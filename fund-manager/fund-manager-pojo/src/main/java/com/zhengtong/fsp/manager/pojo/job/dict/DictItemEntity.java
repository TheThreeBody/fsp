package com.zhengtong.fsp.manager.pojo.job.dict;

import java.util.Date;

public class DictItemEntity {
    private Integer dictItemId;

    private String dictItemCode;

    private String dictItemName;

    private Integer dictId;

    private Date createTime;

    private Date updateTime;

    public Integer getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(Integer dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getDictItemCode() {
        return dictItemCode;
    }

    public void setDictItemCode(String dictItemCode) {
        this.dictItemCode = dictItemCode;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
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