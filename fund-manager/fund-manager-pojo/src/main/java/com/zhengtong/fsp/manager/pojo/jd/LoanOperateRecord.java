package com.zhengtong.fsp.manager.pojo.jd;

import java.io.Serializable;
import java.util.Date;

public class LoanOperateRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7906005263559317287L;
	
	private Integer id;
	
	private Integer orderId;
	
	private Integer vbsId;
	
	private Integer type;
	
	private Integer status;
	
	private String statusDesc="";
	
	private String operater="";
	
	private String operateDesc="";
	
	private Date createTime;
	
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getVbsId() {
		return vbsId;
	}

	public void setVbsId(Integer vbsId) {
		this.vbsId = vbsId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
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
