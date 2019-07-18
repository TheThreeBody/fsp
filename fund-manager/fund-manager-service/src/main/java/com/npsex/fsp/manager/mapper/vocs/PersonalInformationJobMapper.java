package com.npsex.fsp.manager.mapper.vocs;

import java.util.List;
import java.util.Map;

import com.npsex.fsp.manager.pojo.job.grxxsqs.GrxxsqsGenerStatus;
import com.npsex.fsp.manager.pojo.job.grxxsqs.GrxxsqsParams;
import com.npsex.fsp.manager.pojo.quartz.CommonPersonalInfo;

public interface PersonalInformationJobMapper {
	int insertGrxxsqsGenerStatus(GrxxsqsGenerStatus grxxsqsGenerStatus);

	int insertGrxxsqsParams(GrxxsqsParams grxxsqsParams);

	List<CommonPersonalInfo> queryGobooPersonalInfo(Map<String,Object> params);

	List<CommonPersonalInfo> queryHebaoPersonalInfo(Map<String,Object> params);

	List<CommonPersonalInfo> queryEsurfingPersonalInfo(Map<String,Object> params);

	List<CommonPersonalInfo> queryEsurfingCreditPersonalInfo(Map<String,Object> params);
}
