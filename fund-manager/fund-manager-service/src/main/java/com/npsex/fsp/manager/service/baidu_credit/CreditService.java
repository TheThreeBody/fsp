package com.npsex.fsp.manager.service.baidu_credit;

import java.util.List;
import java.util.Map;

import com.npsex.fsp.manager.pojo.BaiduCreditEntity;

public interface CreditService {
	public List<BaiduCreditEntity> queryCreditList(Map<String, Object> map);

	public BaiduCreditEntity selectByProcessId(String processId);
}
