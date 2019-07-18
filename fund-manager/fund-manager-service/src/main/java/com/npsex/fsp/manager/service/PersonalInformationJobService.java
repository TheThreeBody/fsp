package com.npsex.fsp.manager.service;

import java.util.List;

import com.npsex.fsp.manager.pojo.quartz.CommonPersonalInfo;

public interface PersonalInformationJobService {

	List<CommonPersonalInfo> queryPersonalInfo(String partitionDbKeys);

	int batchSavePersonInfo(List<CommonPersonalInfo> result);

}
