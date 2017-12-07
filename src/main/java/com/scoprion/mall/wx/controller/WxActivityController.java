package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.domain.WxGroupOrder;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyResponseData;
import com.scoprion.mall.wx.pay.util.WxPayUtil;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.service.activity.WxActivityService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 *
 * @author fk
 * @created on 2017/11/12.
 */
@RestController
@RequestMapping("wx/activity")
public class WxActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxActivityController.class);

    @Autowired
    private WxActivityService wxActivityService;

    /**
     * 拼团列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Access
    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public PageResult groupList(int pageNo, int pageSize, String activityType) {
        return wxActivityService.groupList(pageNo, pageSize, activityType);
    }

    /**
     * 参加拼团
     *
     * @param wxGroupOrder
     * @param request
     * @return
     */
    @Access(need = false)
    @RequestMapping(value = "/joinGroup", method = RequestMethod.POST)
    public BaseResult joinGroup(@RequestBody WxGroupOrder wxGroupOrder, HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        return wxActivityService.joinGroup(wxGroupOrder, ipAddress);
    }

    /**
     * 接收微信回调(拼团)
     *
     * @param request
     * @return
     */
    @Access(need = false)
    @RequestMapping(value = "/callBack", method = RequestMethod.POST)
    public void callBack(HttpServletRequest request) {
        String inputLine;
        StringBuffer notifyXml = new StringBuffer();
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notifyXml.append(inputLine);
            }
            request.getReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UnifiedOrderNotifyRequestData unifiedOrderNotifyRequestData = WxPayUtil.castXMLStringToUnifiedOrderNotifyRequestData(
                notifyXml.toString());

        //回调出现错误 返回
        if (unifiedOrderNotifyRequestData.getReturn_code().equalsIgnoreCase("FAIL")) {
            LOGGER.error("微信回调出错@------" + unifiedOrderNotifyRequestData.getReturn_msg());
            return;
        }

        BaseResult baseResult = wxActivityService.callBack(unifiedOrderNotifyRequestData);

        //回调成功   通知微信已经接收完成
        if (baseResult.getResult() == 1) {
            UnifiedOrderNotifyResponseData unifiedOrderNotifyResponseData = new UnifiedOrderNotifyResponseData();
            unifiedOrderNotifyResponseData.setReturn_code("SUCCESS");
            unifiedOrderNotifyResponseData.setReturn_msg("OK");
            String responseXML = WxPayUtil.castDataToXMLString(unifiedOrderNotifyResponseData);
            //通知微信回调接收成功
            WxUtil.httpsRequest(WxPayConfig.WECHAT_UNIFIED_ORDER_URL, "POST", responseXML);
        }

    }

}
