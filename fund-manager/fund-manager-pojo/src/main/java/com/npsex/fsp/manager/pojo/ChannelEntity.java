package com.npsex.fsp.manager.pojo;

import com.npsex.fsp.commons.core.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Alias("ChannelEntity")
public class ChannelEntity extends BaseEntity{

    private String name;

    private Integer deleteStatus;

    private Integer locked;

    private String description;

    private String creatorName;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

    private List<UserEntity> userList;

    private List<RoleEntity> roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    public List<RoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "RoleEntity [id=" + id
                + ", name=" + name + ",deleteStatus=" + deleteStatus + ", locked=" + locked +", description=" + description
                + ", creatorName=" + creatorName + ", createTime=" + createTime + ", updateTime=" + updateTime + ", updateBy=" + updateBy
                + ", userList="+ userList + ",roleList=" + roleList + "]";
    }

}