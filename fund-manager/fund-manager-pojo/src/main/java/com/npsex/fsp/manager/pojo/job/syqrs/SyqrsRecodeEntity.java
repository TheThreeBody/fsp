package com.npsex.fsp.manager.pojo.job.syqrs;

import java.util.Date;

public class SyqrsRecodeEntity {
    private Integer generateRecodeId;

    private String syqrsUuid;

    private String fileName;

    private String fileUrl;

    private String fileType;

    private Byte productType;

    private String custIdNo;

    private Date createTime;

    private Date updateTime;

    public Integer getGenerateRecodeId() {
        return generateRecodeId;
    }

    public void setGenerateRecodeId(Integer generateRecodeId) {
        this.generateRecodeId = generateRecodeId;
    }

    public String getSyqrsUuid() {
        return syqrsUuid;
    }

    public void setSyqrsUuid(String syqrsUuid) {
        this.syqrsUuid = syqrsUuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Byte getProductType() {
        return productType;
    }

    public void setProductType(Byte productType) {
        this.productType = productType;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
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