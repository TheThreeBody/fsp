package com.zhengtong.fsp.manager.service.impl;

import com.zhengtong.fsp.commons.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengtong.fsp.manager.mapper.vocs.LogInfoMapper;
import com.zhengtong.fsp.manager.pojo.LogInfoEntity;
import com.zhengtong.fsp.manager.service.LogInfoService;

@Service("logInfoService")
public class LogInfoServiceImpl extends BaseServiceImpl<LogInfoEntity, Long> implements LogInfoService{

	@Autowired
	private LogInfoMapper logInfoMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(logInfoMapper);
	}

	@Override
	public int log(LogInfoEntity logInfo) {
		return logInfoMapper.insert(logInfo);
	}
	
}
