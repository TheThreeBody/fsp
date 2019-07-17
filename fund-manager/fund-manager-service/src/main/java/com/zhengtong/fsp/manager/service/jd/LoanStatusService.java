package com.zhengtong.fsp.manager.service.jd;

import java.util.List;
import java.util.Map;

import com.zhengtong.fsp.manager.pojo.jd.CommonResponse;
import com.zhengtong.fsp.manager.pojo.jd.LoanStatusInfo;

public interface LoanStatusService {

	List<LoanStatusInfo> queryLoanStatusList(Map<String, Object> parameters);

	CommonResponse againLoan(String vbsId, String operator)  throws Exception;

	CommonResponse surrender(String vbsId, String remark, String userAccountName) throws Exception;

}
