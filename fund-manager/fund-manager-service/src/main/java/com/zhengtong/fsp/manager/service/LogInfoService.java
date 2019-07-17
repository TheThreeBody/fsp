package com.zhengtong.fsp.manager.service;

import com.zhengtong.fsp.manager.pojo.LogInfoEntity;

import java.util.List;
import java.util.Map;

public interface LogInfoService {

	public int log(LogInfoEntity logInfo);
	
	public List<LogInfoEntity> queryListByPage(Map<String, Object> parameter);
}
