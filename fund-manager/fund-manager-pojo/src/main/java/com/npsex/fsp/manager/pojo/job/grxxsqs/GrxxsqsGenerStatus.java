package com.npsex.fsp.manager.pojo.job.grxxsqs;

import java.util.Date;

public class GrxxsqsGenerStatus {
    private Long generStatusId;

    private String grxxsqsUuid;

    private Byte generateStatus;

    private Date createTime;

    private Date updateTime;

    public Long getGenerStatusId() {
        return generStatusId;
    }

    public void setGenerStatusId(Long generStatusId) {
        this.generStatusId = generStatusId;
    }

    public String getGrxxsqsUuid() {
        return grxxsqsUuid;
    }

    public void setGrxxsqsUuid(String grxxsqsUuid) {
        this.grxxsqsUuid = grxxsqsUuid;
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