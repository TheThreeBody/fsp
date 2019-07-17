package com.zhengtong.fsp.manager.mapper.job.grxxsqs;


import com.zhengtong.fsp.commons.core.base.BaseMapper;
import com.zhengtong.fsp.manager.pojo.ContractInfoEntity;
import com.zhengtong.fsp.manager.pojo.job.grxxsqs.GrxxsqsGenerStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrxxsqsGenerStatusMapper extends BaseMapper<GrxxsqsGenerStatus, Long> {

//    List<ContractInfoEntity> contractInfoGet(Map<String, Object> map);

    List<ContractInfoEntity> contractInfoGet(@Param(value = "createTime") String createTime);

    int updateByPrimaryKeySelective(GrxxsqsGenerStatus grxxsqsGenerStatus);
}