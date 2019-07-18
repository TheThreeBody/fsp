package com.npsex.fsp.manager.pojo.jd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CreditQuota implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -955147195433879295L;

	private Integer quotaId;

    private Integer custId;

    private Integer processId;

    private Integer status;

    private Date quotaLimitDate;

    private BigDecimal totalAccount;

    private BigDecimal account;

    private BigDecimal frozenAccount;

    private BigDecimal monthRate;

    private BigDecimal monthlyInterestRate;

    private BigDecimal monthlyServiceRate;

    private BigDecimal formalitiesRate;

    private BigDecimal dayrate;

    private BigDecimal dayinterestrate;

    private BigDecimal dayservicerate;

    private Date createTime=new Date();

    private Date updateTime;

    public Integer getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Integer quotaId) {
        this.quotaId = quotaId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getQuotaLimitDate() {
        return quotaLimitDate;
    }

    public void setQuotaLimitDate(Date quotaLimitDate) {
        this.quotaLimitDate = quotaLimitDate;
    }

    public BigDecimal getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(BigDecimal totalAccount) {
        this.totalAccount = totalAccount;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getFrozenAccount() {
        return frozenAccount;
    }

    public void setFrozenAccount(BigDecimal frozenAccount) {
        this.frozenAccount = frozenAccount;
    }

    public BigDecimal getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(BigDecimal monthRate) {
        this.monthRate = monthRate;
    }

    public BigDecimal getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(BigDecimal monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public BigDecimal getMonthlyServiceRate() {
        return monthlyServiceRate;
    }

    public void setMonthlyServiceRate(BigDecimal monthlyServiceRate) {
        this.monthlyServiceRate = monthlyServiceRate;
    }

    public BigDecimal getFormalitiesRate() {
        return formalitiesRate;
    }

    public void setFormalitiesRate(BigDecimal formalitiesRate) {
        this.formalitiesRate = formalitiesRate;
    }

    public BigDecimal getDayrate() {
        return dayrate;
    }

    public void setDayrate(BigDecimal dayrate) {
        this.dayrate = dayrate;
    }

    public BigDecimal getDayinterestrate() {
        return dayinterestrate;
    }

    public void setDayinterestrate(BigDecimal dayinterestrate) {
        this.dayinterestrate = dayinterestrate;
    }

    public BigDecimal getDayservicerate() {
        return dayservicerate;
    }

    public void setDayservicerate(BigDecimal dayservicerate) {
        this.dayservicerate = dayservicerate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}