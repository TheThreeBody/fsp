package com.zhengtong.fsp.manager.pojo;

import com.zhengtong.fsp.commons.core.base.BaseEntity;

/**
 * Created by dongwen on 2017/7/14.
 */
public class FrozenEntity extends BaseEntity {

    private int creditId;
    private String creditAmount;
    private String creditAmountFrozen;
    private String creditAmountUsed;
    private String creditExpire;
    private String creditStatus;
    private String orderId;

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getCreditAmountFrozen() {
        return creditAmountFrozen;
    }

    public void setCreditAmountFrozen(String creditAmountFrozen) {
        this.creditAmountFrozen = creditAmountFrozen;
    }

    public String getCreditAmountUsed() {
        return creditAmountUsed;
    }

    public void setCreditAmountUsed(String creditAmountUsed) {
        this.creditAmountUsed = creditAmountUsed;
    }

    public String getCreditExpire() {
        return creditExpire;
    }

    public void setCreditExpire(String creditExpire) {
        this.creditExpire = creditExpire;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
