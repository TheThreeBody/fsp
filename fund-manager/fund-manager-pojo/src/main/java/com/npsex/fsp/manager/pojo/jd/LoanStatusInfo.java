package com.npsex.fsp.manager.pojo.jd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dell
 *
 */
public class LoanStatusInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7695943931289607605L;
	
	private String orderId;
	
	private String custId;
	
	private Integer vbsId;
	
	private String name;
	
	private String custMobile;
	
	private String identityNo;
	
	private String loanNo;
	
	private String bank;
	
	private String cardNo;
	
	private Date loanTime;
	
	private Date paymentTime;
	
	private Integer loanTerm;
	
	private Integer status;
	
	private BigDecimal loanAmount;
	
	private String vbsNotifyType;
	
	private String vbsNotifyNum;
	
	private String orderDesc;
	
	private String operateDesc;
	
	private String operateType;
	
	private String operateStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Integer getVbsId() {
		return vbsId;
	}

	public void setVbsId(Integer vbsId) {
		this.vbsId = vbsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getVbsNotifyType() {
		return vbsNotifyType;
	}

	public void setVbsNotifyType(String vbsNotifyType) {
		this.vbsNotifyType = vbsNotifyType;
	}

	public String getVbsNotifyNum() {
		return vbsNotifyNum;
	}

	public void setVbsNotifyNum(String vbsNotifyNum) {
		this.vbsNotifyNum = vbsNotifyNum;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	
	
	
	

}
