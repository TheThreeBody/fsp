package com.npsex.fsp.manager.mapper.jd;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.npsex.fsp.manager.pojo.jd.CreditQuota;
import com.npsex.fsp.manager.pojo.jd.LoanOperateRecord;
import com.npsex.fsp.manager.pojo.jd.LoanStatusInfo;

@Repository
public interface LoanStatusInfoMapper {

	List<LoanStatusInfo> queryLoanStatusList(Map<String, Object> parameters);

	LoanOperateRecord queryLoanOperateRecord(LoanOperateRecord record);

	int saveLoanOperateRecord(LoanOperateRecord record);

	int updateOrder(Integer orderId);

	int updateOrderStatus(Integer orderId);

	CreditQuota getValidCreditQuota(String custId);

	int frozenAmount(Map<String, Object> parameters);

	int updateOrderLoanTobe(Integer orderId);

	int updateOrderStatusLoanTobe(Integer orderId);

}
