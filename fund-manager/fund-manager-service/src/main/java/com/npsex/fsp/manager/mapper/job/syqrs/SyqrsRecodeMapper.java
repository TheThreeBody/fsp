package com.npsex.fsp.manager.mapper.job.syqrs;


import com.npsex.fsp.manager.pojo.job.syqrs.SyqrsRecodeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SyqrsRecodeMapper {

    public void recodeInsert(SyqrsRecodeEntity syqrsRecodeEntity);

}