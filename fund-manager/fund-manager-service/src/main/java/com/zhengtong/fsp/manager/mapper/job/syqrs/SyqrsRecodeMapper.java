package com.zhengtong.fsp.manager.mapper.job.syqrs;


import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsRecodeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SyqrsRecodeMapper {

    public void recodeInsert(SyqrsRecodeEntity syqrsRecodeEntity);

}