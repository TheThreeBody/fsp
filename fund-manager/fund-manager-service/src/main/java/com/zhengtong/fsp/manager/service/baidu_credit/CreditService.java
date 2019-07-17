package com.zhengtong.fsp.manager.service.baidu_credit;

import java.util.List;
import java.util.Map;

import com.zhengtong.fsp.manager.pojo.BaiduCreditEntity;
import com.zhengtong.fsp.manager.pojo.CashEntity;

public interface CreditService {
	public List<BaiduCreditEntity> queryCreditList(Map<String, Object> map);

	public BaiduCreditEntity selectByProcessId(String processId);
}
