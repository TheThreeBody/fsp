package com.zhengtong.fsp.manager.service;

import com.zhengtong.fsp.manager.pojo.LoginInfoEntity;

import java.util.List;
import java.util.Map;

public interface LoginInfoService {

	public int log(LoginInfoEntity loginInfo);
	
	public List<LoginInfoEntity> queryListByPage(Map<String, Object> parameter);
}
