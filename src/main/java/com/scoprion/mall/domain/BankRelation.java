package com.scoprion.mall.domain;

/**
 * 银行卡关系
 * Created on 2017/10/10.
 */
public class BankRelation {

    //主键
    private Long id;

    //三方id
    private Long thirdId;

    //银行卡Id
    private Long bankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "BankRelation{" +
                "id=" + id +
                ", thirdId=" + thirdId +
                ", bankId=" + bankId +
                '}';
    }
}
