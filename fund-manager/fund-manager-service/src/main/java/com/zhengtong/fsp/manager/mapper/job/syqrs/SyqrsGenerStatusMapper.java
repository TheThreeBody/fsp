package com.zhengtong.fsp.manager.mapper.job.syqrs;



import com.zhengtong.fsp.commons.core.base.BaseMapper;
import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsGenerStatusEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SyqrsGenerStatusMapper extends BaseMapper<SyqrsGenerStatusEntity, Long> {

   public int updateStatus(SyqrsGenerStatusEntity record);

    int insertSelective(SyqrsGenerStatusEntity rocord) throws Exception;

}