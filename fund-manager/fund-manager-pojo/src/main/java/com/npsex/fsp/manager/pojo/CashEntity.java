package com.npsex.fsp.manager.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.npsex.fsp.commons.core.base.BaseEntity;

public class CashEntity  extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String custId;
    private String transactionApplyId;
    private String name;
    private String identityNo;
    private String mobile;
    private String transactionNode;
    private Date beginTime;
    private Date endTime;
    private BigDecimal amount;
    private BigDecimal valueAmount;
    private String custLevel;
    private String  remark;
    private String vauleAccount;
    private BigDecimal dayRate;
    private String transactionId;
    private String retCode;
    private String creditScore;
    private String applyResult;
	public String getApplyResult() {
		return applyResult;
	}
	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}
	public String getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public BigDecimal getDayRate() {
		return dayRate;
	}
	public void setDayRate(BigDecimal dayRate) {
		this.dayRate = dayRate;
	}
	public String getVauleAccount() {
		return vauleAccount;
	}
	public void setVauleAccount(String vauleAccount) {
		this.vauleAccount = vauleAccount;
	}
	public String getTransactionApplyId() {
		return transactionApplyId;
	}
	public void setTransactionApplyId(String transactionApplyId) {
		this.transactionApplyId = transactionApplyId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTransactionNode() {
		return transactionNode;
	}
	public void setTransactionNode(String transactionNode) {
		this.transactionNode = transactionNode;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getValueAmount() {
		return valueAmount;
	}
	public void setValueAmount(BigDecimal valueAmount) {
		this.valueAmount = valueAmount;
	}
    

}
