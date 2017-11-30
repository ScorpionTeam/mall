package com.scoprion.mall.backstage.service.seller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Seller;
import com.scoprion.mall.backstage.mapper.SellerMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IDWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/10/10.
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 创建商户
     *
     * @param seller
     * @return
     */
    @Override
    public BaseResult add(Seller seller) throws Exception {
        int validName = sellerMapper.validByName(seller.getSellerName());
        if (validName != 0) {
            return BaseResult.error("ERROR", "店铺名称已经存在");
        }
        Long sellerNo = IDWorker.getFlowIdWorkerInstance().nextId();
        seller.setSellerNo(sellerNo.toString());
        sellerMapper.add(seller);
        return BaseResult.success("店铺创建成功");

    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteByPrimaryKey(Long id) {
        return null;
    }

    /**
     * 编辑商户信息
     *
     * @param seller
     * @return
     */
    @Override
    public BaseResult edit(Seller seller) {
        return null;
    }

    /**
     * 分页查询商户信息
     *
     * @param pageNo
     * @param pageSize
     * @param sellerName
     * @return
     */
    @Override
    public PageResult listByPage(int pageNo, int pageSize, String sellerName) {
        PageHelper.startPage(pageNo, pageSize);
        if (!StringUtils.isEmpty(sellerName)) {
            sellerName = "%" + sellerName + "%";
        }
        Page<Seller> page = sellerMapper.listByPage(sellerName);
        return new PageResult(page);
    }

    /**
     * 查询商户详细信息
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult sellerInfo(Long id) {
        return null;
    }
}
