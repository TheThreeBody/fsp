package com.npsex.fsp.manager.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.npsex.fsp.commons.core.base.BaseEntity;

public class BaiduCreditEntity  extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String custId;
	private String name;
	private String identityNo;
	private String mobile;
	private Integer creditStatus;//授信状态
	private Date beginTime;
	private Date  endTime;
	private BigDecimal initialAmount; //
	private BigDecimal creditAmount;//决策
	private BigDecimal totolAmount;//渠道给
	private BigDecimal account;//已用额度
	private BigDecimal frozenAccount;//冻结额度
	private String custLevel;//客户评级
	private BigDecimal   dayRate;//日服务费率
	private String processNode;//拒绝阶段
	private String creditMsg;//内部原因
	private Date    creditExpirationDate;//有效期
    private BigDecimal vauleAccount;
    private String processId;
    private String creditScore;//征信模型分
    private String  reasonMsg;
    private String  amountStatus;//额度状态
	public String getAmountStatus() {
		return amountStatus;
	}
	public void setAmountStatus(String amountStatus) {
		this.amountStatus = amountStatus;
	}
	public String getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	public String getReasonMsg() {
		return reasonMsg;
	}
	public void setReasonMsg(String reasonMsg) {
		this.reasonMsg = reasonMsg;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public BigDecimal getVauleAccount() {
		return vauleAccount;
	}
	public void setVauleAccount(BigDecimal vauleAccount) {
		this.vauleAccount = vauleAccount;
	}
	public Date getCreditExpirationDate() {
		return creditExpirationDate;
	}
	public void setCreditExpirationDate(Date creditExpirationDate) {
		this.creditExpirationDate = creditExpirationDate;
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
	public Integer getCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus(Integer creditStatus) {
		this.creditStatus = creditStatus;
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
	public BigDecimal getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public BigDecimal getTotolAmount() {
		return totolAmount;
	}
	public void setTotolAmount(BigDecimal totolAmount) {
		this.totolAmount = totolAmount;
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
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public BigDecimal getDayRate() {
		return dayRate;
	}
	public void setDayRate(BigDecimal dayRate) {
		this.dayRate = dayRate;
	}
	public String getProcessNode() {
		return processNode;
	}
	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}
	public String getCreditMsg() {
		return creditMsg;
	}
	public void setCreditMsg(String creditMsg) {
		this.creditMsg = creditMsg;
	}
	
	
	
	
} 
