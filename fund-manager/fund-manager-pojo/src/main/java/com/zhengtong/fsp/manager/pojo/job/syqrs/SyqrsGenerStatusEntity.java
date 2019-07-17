package com.zhengtong.fsp.manager.pojo.job.syqrs;

import java.util.Date;

public class SyqrsGenerStatusEntity {
    private Long generStatusId;

    private String syqrsUuid;

    private Byte generateStatus;

    private Date createTime;

    private Date updateTime;

    public Long getGenerStatusId() {
        return generStatusId;
    }

    public void setGenerStatusId(Long generStatusId) {
        this.generStatusId = generStatusId;
    }

    public String getSyqrsUuid() {
        return syqrsUuid;
    }

    public void setSyqrsUuid(String syqrsUuid) {
        this.syqrsUuid = syqrsUuid;
    }

    public Byte getGenerateStatus() {
        return generateStatus;
    }

    public void setGenerateStatus(Byte generateStatus) {
        this.generateStatus = generateStatus;
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