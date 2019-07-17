package com.zhengtong.fsp.manager.pojo.quartz;

import java.io.Serializable;
import java.util.Date;

public class CommonPersonalInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1248912575283281482L;

	private Integer productType;
	
	private String custId;
	
	private Integer contactType=1;
	
	private Date creditTime;
	
	private String custIdNo;
	
	private String custName;
	
	private String custMobile;

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Integer getContactType() {
		return contactType;
	}

	public void setContactType(Integer contactType) {
		this.contactType = contactType;
	}

	public Date getCreditTime() {
		return creditTime;
	}

	public void setCreditTime(Date creditTime) {
		this.creditTime = creditTime;
	}

	public String getCustIdNo() {
		return custIdNo;
	}

	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

}
