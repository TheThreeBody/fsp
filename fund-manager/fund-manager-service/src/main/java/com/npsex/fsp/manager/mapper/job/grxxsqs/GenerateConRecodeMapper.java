package com.npsex.fsp.manager.mapper.job.grxxsqs;

import com.npsex.fsp.manager.pojo.job.grxxsqs.GenerateConRecode;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateConRecodeMapper {
    int deleteByPrimaryKey(Integer generate_recode_id);

    int insert(GenerateConRecode record);

    int insertSelective(GenerateConRecode record);

    GenerateConRecode selectByPrimaryKey(Integer generate_recode_id);

    int updateByPrimaryKeySelective(GenerateConRecode record);

    int updateByPrimaryKey(GenerateConRecode record);
}