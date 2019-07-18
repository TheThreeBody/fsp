package com.npsex.fsp.manager.service.baidu_credit;

import java.util.List;
import java.util.Map;
/**
 * 异常查询和放款查询
 * @author dell
 *
 */
public interface QueryDataService {
	public List<Map<String,Object>> queryIssue(String beginDate,String endDate);
	public List<Map<String,Object>> queryIssueDetail(Long id, String type);
	public int updateIssueData(Map<String, Object> param);
	public List<Map<String,Object>> queryloanData(Map<String, Object> param);
	public Map cancleOrSubmit(String type, String vbs_bid, String comment);
	public int updateAccountStatus(Map<String, Object> param);
	

}
