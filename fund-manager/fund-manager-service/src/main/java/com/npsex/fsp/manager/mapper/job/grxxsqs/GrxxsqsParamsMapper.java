package com.npsex.fsp.manager.mapper.job.grxxsqs;


import com.npsex.fsp.manager.pojo.job.grxxsqs.GrxxsqsParams;
import org.springframework.stereotype.Repository;

@Repository
public interface GrxxsqsParamsMapper {
    int deleteByPrimaryKey(Long grxxsqs_id);

    int insert(GrxxsqsParams record);

    int insertSelective(GrxxsqsParams record);

    GrxxsqsParams selectByPrimaryKey(Long grxxsqs_id);

    int updateByPrimaryKeySelective(GrxxsqsParams record);

    int updateByPrimaryKey(GrxxsqsParams record);
}