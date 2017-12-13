package com.scoprion.mall.seller.service.ticket;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.scoprion.mall.domain.Ticket;
import com.scoprion.mall.seller.mapper.SellerTicketMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by fk
 * @created on 2017/12/13.
 */
@Service
public class SellerTicketServiceImpl implements SellerTicketService {

    @Autowired
    private SellerTicketMapper sellerTicketMapper;

    /**
     * 优惠卷列表
     *
     * @param pageNo
     * @param pageSize
     * @param sellerId
     * @return
     */
    @Override
    public PageResult findAll(int pageNo, int pageSize, Long sellerId) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtil.isEmpty(sellerId.toString())) {
            return new PageResult();
        }
        Page<Ticket> page = sellerTicketMapper.findAll(sellerId);
        if (page == null) {
            return new PageResult();
        }
        return new PageResult(page);
    }
}
