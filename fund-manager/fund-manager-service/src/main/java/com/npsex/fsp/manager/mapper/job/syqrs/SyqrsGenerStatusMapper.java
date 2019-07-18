package com.npsex.fsp.manager.mapper.job.syqrs;



import com.npsex.fsp.commons.core.base.BaseMapper;
import com.npsex.fsp.manager.pojo.job.syqrs.SyqrsGenerStatusEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SyqrsGenerStatusMapper extends BaseMapper<SyqrsGenerStatusEntity, Long> {

   public int updateStatus(SyqrsGenerStatusEntity record);

    int insertSelective(SyqrsGenerStatusEntity rocord) throws Exception;

}