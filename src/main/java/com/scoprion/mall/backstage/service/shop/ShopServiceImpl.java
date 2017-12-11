package com.scoprion.mall.backstage.service.shop;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.ShopMapper;
import com.scoprion.mall.domain.Seller;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by hmy
 * @created on 2017/12/11/011.
 */
@Service
public class ShopServiceImpl implements ShopService {


    @Autowired
    private ShopMapper shopMapper;


    /**
     * 商铺列表
     *
     * @param pageNo
     * @param pageSize
     * @param audit
     * @return
     */
    @Override
    public PageResult listPage(Integer pageNo, Integer pageSize, String audit) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Seller> page = shopMapper.listPage(audit);
        return new PageResult(page);
    }

    /**
     * 店铺审核
     * @param audit
     * @param reason
     * @param id
     * @return
     */
    @Override
    public BaseResult audit(String audit, String reason,Long id) {
        if (StringUtils.isEmpty(id.toString())){
            return BaseResult.parameterError();
        }
        if (audit.equals(CommonEnum.PASS_AUDIT.getCode())) {
            Integer result = shopMapper.update(audit, null,id);
            if (result < 0) {
                return BaseResult.error("ERROR", "审核失败");
            }
            return BaseResult.success("审核通过");
        }
        if (StringUtils.isEmpty(reason)) {
            return BaseResult.error("ERROR", "请填写失败原因");
        }
        Integer auditResult = shopMapper.update(audit, reason,id);
        if (auditResult < 0) {
            return BaseResult.error("ERROR", "审核失败");
        }
        return BaseResult.success("审核不通过");
    }
}
