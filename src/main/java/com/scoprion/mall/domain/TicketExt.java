package com.scoprion.mall.domain;

/**
 * @author by hmy
 * @created on 2017/11/14/014.
 */
public class TicketExt extends Ticket {
    /**
     * 用户id
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
