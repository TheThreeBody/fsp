package com.zhengtong.fsp.manager.mapper.baidu_credit;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhengtong.fsp.manager.pojo.CashEntity;

@Repository
public interface CashMapper {
	public List<CashEntity> queryCashList(Map<String, Object> map);
	public CashEntity  findCashDetail(String transactionApplyId);
	public   void     solutionException(Map<String,Object> map);
}
