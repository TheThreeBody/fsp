package com.zhengtong.fsp.manager.pojo.job.grxxsqs;

import java.util.Date;

public class GenerateConRecode {
    private Integer generateRecodeId;

    private String grxxsqsUuid;

    private String fileName;

    private String fileUrl;

    private String fileType;

    private String custId;

    private byte productType;

    private Date createTime;

    private Date updateTime;

    public Integer getGenerateRecodeId() {
        return generateRecodeId;
    }

    public void setGenerateRecodeId(Integer generateRecodeId) {
        this.generateRecodeId = generateRecodeId;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Byte getProductType() {
        return productType;
    }

    public void setProductType(Byte productType) {
        this.productType = productType;
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

    public String getGrxxsqsUuid() {
        return grxxsqsUuid;
    }

    public void setGrxxsqsUuid(String grxxsqsUuid) {
        this.grxxsqsUuid = grxxsqsUuid;
    }
}