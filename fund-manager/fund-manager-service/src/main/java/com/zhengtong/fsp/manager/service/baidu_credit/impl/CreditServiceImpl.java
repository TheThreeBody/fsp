package com.zhengtong.fsp.manager.service.baidu_credit.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengtong.fsp.manager.mapper.baidu_credit.CreditMapper;
import com.zhengtong.fsp.manager.pojo.BaiduCreditEntity;
import com.zhengtong.fsp.manager.pojo.CashEntity;
import com.zhengtong.fsp.manager.service.baidu_credit.CreditService;

@Service
public class CreditServiceImpl implements CreditService{
	@Autowired
	private CreditMapper creditMapper;

	@Override
	public List<BaiduCreditEntity> queryCreditList(Map<String, Object> map) {
		return creditMapper.queryCreditList(map);
	}

	@Override
	public BaiduCreditEntity selectByProcessId(String processId) {
		return creditMapper.selectByProcessId(processId);
	}

}
