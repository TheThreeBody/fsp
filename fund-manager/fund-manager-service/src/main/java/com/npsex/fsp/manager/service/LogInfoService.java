package com.npsex.fsp.manager.service;

import com.npsex.fsp.manager.pojo.LogInfoEntity;

import java.util.List;
import java.util.Map;

public interface LogInfoService {

	public int log(LogInfoEntity logInfo);
	
	public List<LogInfoEntity> queryListByPage(Map<String, Object> parameter);
}
