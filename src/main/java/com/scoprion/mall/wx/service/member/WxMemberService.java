package com.scoprion.mall.wx.service.member;

import com.scoprion.mall.domain.Suggest;
import com.scoprion.result.BaseResult;
import org.springframework.stereotype.Service;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-17 10:18
 */
public interface WxMemberService {

    /**
     * 意见、建议
     *
     * @param suggest
     * @return
     */
    BaseResult suggest(Suggest suggest);
}
