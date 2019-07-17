package com.zhengtong.fsp.manager.mapper.baidu_credit;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhengtong.fsp.manager.pojo.BaiduCreditEntity;

@Repository
public interface CreditMapper {
	public List<BaiduCreditEntity> queryCreditList(Map<String, Object> map);

	public BaiduCreditEntity selectByProcessId(String processId);
}
