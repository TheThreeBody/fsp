package com.zhengtong.fsp.manager.service.baidu_credit.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhengtong.fsp.commons.utils.HttpClientUtil;
import com.zhengtong.fsp.commons.utils.JsonUtils;
import com.zhengtong.fsp.manager.mapper.baidu_credit.IssueDataMapper;
import com.zhengtong.fsp.manager.pojo.UserEntity;
import com.zhengtong.fsp.manager.service.baidu_credit.QueryDataService;
/**
 * 异常查询和放款查询
 * @author dell
 *
 */
@Service
public class QueryDataServiceImpl implements QueryDataService{
	private static final Logger logger = LoggerFactory.getLogger(QueryDataServiceImpl.class);
	public static final String BD_LOAND_KIND = "LOANKIND/JIEXIANJIN";
	@Value("${JXJReSubmitLoan}")
	private String jXJReSubmitLoan; // 提交放款(第三方放款失败再次提交放款)
	
	@Value("${JXJCancelContract}")
	private String jXJCancelContract; // 解约
	
	@Autowired
	private IssueDataMapper issueDataMapper;
	@Override
	public List<Map<String, Object>> queryIssue(String beginDate, String endDate) {
		return issueDataMapper.queryIssue(beginDate, endDate);
	}
	@Override
	public List<Map<String, Object>> queryIssueDetail(Long id, String type) {
		return  issueDataMapper.queryIssueDetail(id, type);
	}
	@Override
	public int updateIssueData(Map<String, Object> param) {
		return issueDataMapper.updateIssueData(param);
	}
	@Override
	public List<Map<String, Object>> queryloanData(Map<String, Object> param) {
		return issueDataMapper.queryloanData(param);
	}
	@Override
	public Map cancleOrSubmit(String type, String vbs_bid,String comment) {
		Map<String, Object> param = new HashMap<String, Object>();
		Integer vbs = Integer.parseInt(vbs_bid);
		param.put("Bid", vbs);
		param.put("LoanKind",BD_LOAND_KIND);
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
    	String name = userEntity.getUserName();
		if ("cancle".equals(type)) { // 解约
			try {
				String paramStr = JsonUtils.Obj2Json(param);
				logger.info("解约请求参数-->{}",paramStr);
				String resultStr = HttpClientUtil.doPostJson(jXJCancelContract, paramStr);
				logger.info("解约返回参数-->{}",resultStr);
				Map<String, Object> result = JsonUtils.Json2Map(resultStr);
				Integer status = (Integer)result.get("Status");
				if (status.equals(1) ) { // Status=0；失败;  Status=1；成功,-1异常
					Map<String, Object> var1 = new HashMap<String, Object>();
					var1.put("vbs_bid", vbs);
					var1.put("order_status", "7"); // 7 解约
					var1.put("update_time",new Date());
					issueDataMapper.updateLoanData(var1); // 更新订单的状态
					issueDataMapper.releaseFrozenAccount(vbs,new Date()); // 释放额度
					Map<String, Object> record = new HashMap<String, Object>();
					record.put("vbs_bid", vbs);
					record.put("operation_status", 0);
					record.put("opetation_msg", comment);
					record.put("operation_person", name);
					record.put("create_time", new Date());
					record.put("update_time", record.get("create_time"));
					List<Map<String, Object>> list = issueDataMapper.selectLoanOperationRecord(vbs);
					if (list != null && list.size()>0){
						issueDataMapper.updateLoanOperationRecord(record);
					}else {
						issueDataMapper.insertLoanOperationRecord(record);
					}
					Map<String,String> res = new HashMap<String, String>();
					res.put("code", "000000");
					res.put("msg","成功了");
					return res;
				}else {
					Map<String,String> res = new HashMap<String, String>();
					res.put("code", "000001");
					res.put("msg", String.valueOf(result.get("Exception")));
					return res;
				}
			} catch (Exception e) {
				e.printStackTrace();
				Map<String,String> res = new HashMap<String, String>();
				res.put("code", "000002");
				res.put("msg", "系统异常了");
				return res;
			}
		}
		if ("loan".equals(type)) {
			try {
				String paramStr = JsonUtils.Obj2Json(param);
				logger.info("请求重新放款-->{}",paramStr);
				String resultStr = HttpClientUtil.doPostJson(jXJReSubmitLoan, paramStr);
				logger.info("请求重新放款返回-->{}",resultStr);
				Map<String, Object> result = JsonUtils.Json2Map(resultStr);
				Integer status = (Integer)result.get("Status");
				if (status.equals(1)) { // Status=0；失败;  Status=1；成功,-1异常
					Map<String, Object> var1 = new HashMap<String, Object>();
					var1.put("vbs_bid", vbs);
					var1.put("order_status", "3"); // 状态改为3
					var1.put("update_time",new Date());
					issueDataMapper.updateLoanData(var1); // 更新订单的状态
					Map<String, Object> record = new HashMap<String, Object>();
					record.put("vbs_bid", vbs);
					record.put("operation_status", 1);
					record.put("opetation_msg", comment);
					record.put("operation_person", name);
					record.put("create_time", new Date());
					record.put("update_time", record.get("create_time"));
					List<Map<String, Object>> list = issueDataMapper.selectLoanOperationRecord(vbs);
					if (list != null && list.size()>0){
						issueDataMapper.updateLoanOperationRecord(record);
					}else {
						issueDataMapper.insertLoanOperationRecord(record);
					}
					Map<String,String> res = new HashMap<String, String>();
					res.put("code", "000000");
					res.put("msg", "成功了");
					return res;
				} else {
					Map<String,String> res = new HashMap<String, String>();
					res.put("code", "000001");
					res.put("msg", String.valueOf(result.get("Exception")));
					return res;
				}
			} catch (Exception e) {
				e.printStackTrace();
				Map<String,String> res = new HashMap<String, String>();
				res.put("code", "000002");
				res.put("msg", "系统异常了");
				return res;
			} 
		}
		Map<String,String> res = new HashMap<String, String>();
		res.put("code", "000003");
		res.put("msg", "系统异常了");
		return res;	
	}
	@Override
	public int updateAccountStatus(Map<String, Object> param) {
		return issueDataMapper.updateAccountStatus(param);
	}

}
