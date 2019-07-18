package com.npsex.fsp.manager.mapper.job.syqrs;


import com.npsex.fsp.commons.core.base.BaseMapper;
import com.npsex.fsp.manager.pojo.job.syqrs.SyqrsParamsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyqrsParamsMapper extends BaseMapper<SyqrsParamsEntity, Long> {


    /**
     * 根据消费时间查询生成合同失败或者未生成合同的数据记录
     * @param consumeTime 消费时间
     * @return
     */
      List<SyqrsParamsEntity> findSyqrsDataByParams(@Param(value="consumeTime")String consumeTime);

      int insertSelective(SyqrsParamsEntity paramsEntity) throws Exception;


}