package com.scoprion.mall.wx.service.member;

import com.alibaba.druid.util.StringUtils;
import com.scoprion.mall.domain.Suggest;
import com.scoprion.mall.wx.mapper.WxMemberMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-17 10:18
 */
@Service
public class WxMemberServiceImpl implements WxMemberService {

    @Autowired
    WxMemberMapper wxMemberMapper;

    /**
     * 意见、建议
     *
     * @param suggest
     * @return
     */
    @Override
    public BaseResult suggest(Suggest suggest) {
        if (StringUtils.isEmpty(suggest.getContent())) {
            return BaseResult.error("ERROR", "内容不能为空");
        }
        if (StringUtils.isEmpty(suggest.getWxCode())) {
            return BaseResult.parameterError();
        }
        suggest.setUserId(WxUtil.getOpenId(suggest.getWxCode()));
        int result = wxMemberMapper.add(suggest);
        if (result > 0) {
            return BaseResult.success("提交成功");
        }
        return BaseResult.error("ERROR", "提交失败");

    }
}
