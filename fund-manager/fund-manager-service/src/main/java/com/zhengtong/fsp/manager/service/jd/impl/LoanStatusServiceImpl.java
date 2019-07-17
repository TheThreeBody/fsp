package com.zhengtong.fsp.manager.service.jd.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhengtong.fsp.commons.utils.HttpRequest;
import com.zhengtong.fsp.commons.utils.VcreditUtils;
import com.zhengtong.fsp.manager.mapper.jd.LoanStatusInfoMapper;
import com.zhengtong.fsp.manager.pojo.common.ApiConstant;
import com.zhengtong.fsp.manager.pojo.jd.CommonResponse;
import com.zhengtong.fsp.manager.pojo.jd.CreditQuota;
import com.zhengtong.fsp.manager.pojo.jd.LoanOperateRecord;
import com.zhengtong.fsp.manager.pojo.jd.LoanStatusInfo;
import com.zhengtong.fsp.manager.service.jd.LoanStatusService;

@Service
public class LoanStatusServiceImpl implements LoanStatusService {

	/** 再次放款 */
	public static final int AGAINLOAN_TYPE = 1;
	/** 解约 */
	public static final int SURRENDER_TYPE = 2;
	/** 成功 */
	public static final int SUCCESS_STATUS = 1;
	/** 失败 */
	public static final int FAILED_STATUS = 2;

	public Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private LoanStatusInfoMapper loanStatusInfoMapper;

	@Override
	public List<LoanStatusInfo> queryLoanStatusList(Map<String, Object> parameters) {
		return loanStatusInfoMapper.queryLoanStatusList(parameters);
	}

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class,
            Exception.class})
	@Override
	public CommonResponse againLoan(String vbsId, String operator) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("vbsId", vbsId);
		List<LoanStatusInfo> list = loanStatusInfoMapper.queryLoanStatusList(parameters);
		if (list.size() == 0) {
			logger.error("{}没有查询到订单信息", vbsId);
			return CommonResponse.newFailedResponse().setMsg("没有查询到订单信息");
		}

		LoanOperateRecord record = new LoanOperateRecord();
		record.setType(AGAINLOAN_TYPE);
		record.setStatus(SUCCESS_STATUS);
		record.setOrderId(Integer.valueOf(list.get(0).getOrderId()));
		record.setVbsId(Integer.valueOf(vbsId));
		record = loanStatusInfoMapper.queryLoanOperateRecord(record);

		if (record != null) {
			logger.error("{}订单已经成功再次放款", vbsId);
			return CommonResponse.newFailedResponse().setMsg("该订单已提交过再次放款");
		}
		record = new LoanOperateRecord();
		record.setType(AGAINLOAN_TYPE);
		record.setOrderId(Integer.valueOf(list.get(0).getOrderId()));
		record.setVbsId(Integer.valueOf(vbsId));
		record.setOperateDesc("");
		record.setOperater(operator);
		boolean flag = true;
		try {
			Map<String, Object> result  = syhResubmitloan(vbsId);
			if (result.get("Status") != null && "1".equals(result.get("Status").toString())) {
				record.setStatus(SUCCESS_STATUS);
				record.setStatusDesc("再次放款成功");
				loanStatusInfoMapper.updateOrderLoanTobe(record.getOrderId());
				loanStatusInfoMapper.updateOrderStatusLoanTobe(record.getOrderId());
				return CommonResponse.newSuccessResponse().setData("再次放款成功");
			} else {
				logger.error("再次放款异常：" + result.get("Exception"));
				record.setStatus(FAILED_STATUS);
				record.setStatusDesc(StringUtils.substring(result.get("Exception").toString(), 0,254));
				return CommonResponse.newFailedResponse().setMsg(record.getStatusDesc());
			}
		} catch (Exception e) {
			logger.error("再次放款接口发生异常：", e);
			flag = false;
			throw e;
		}finally{
			if(flag){
				logger.info("保存订单记录表,再次放款："+loanStatusInfoMapper.saveLoanOperateRecord(record));
			}
			
		}
	}


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class,
            Exception.class})
	@Override
	public CommonResponse surrender(String vbsId, String remark, String userAccountName) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("vbsId", vbsId);
		List<LoanStatusInfo> list = loanStatusInfoMapper.queryLoanStatusList(parameters);
		if (list.size() == 0) {
			logger.error("{}没有查询到订单信息", vbsId);
			return CommonResponse.newFailedResponse().setMsg("没有查询到订单信息");
		}

		LoanOperateRecord record = new LoanOperateRecord();
		record.setType(SURRENDER_TYPE);
		record.setOrderId(Integer.valueOf(list.get(0).getOrderId()));
		record.setVbsId(Integer.valueOf(vbsId));
		record.setOperateDesc(remark);
		record.setOperater(userAccountName);
		boolean flag = true;
		try {
			Map<String, Object> result  = syhCancelContract(vbsId);
			if (result.get("Status") != null && "1".equals(result.get("Status").toString())) {
				record.setStatus(SUCCESS_STATUS);
				record.setStatusDesc("解约成功");
				
				//TODO 将订单状态改为7,并释放冻结金额
				loanStatusInfoMapper.updateOrder(record.getOrderId());
				loanStatusInfoMapper.updateOrderStatus(record.getOrderId());
				
				unfrozenCredit(list.get(0).getCustId(),list.get(0).getLoanAmount());
				
				return CommonResponse.newSuccessResponse().setData("解约成功");
			} else {
				logger.error("解约异常：" + result.get("Exception"));
				record.setStatus(FAILED_STATUS);
				record.setStatusDesc(StringUtils.substring(result.get("Exception").toString(), 0,254));
				return CommonResponse.newFailedResponse().setMsg(record.getStatusDesc());
			}
		} catch (Exception e) {
			logger.error("解约接口发生异常：", e);
			throw e;
		}finally{
			if(flag){
				logger.info("保存订单操作记录表,解约："+loanStatusInfoMapper.saveLoanOperateRecord(record));
			}
			
		}
	}

	/**
	 * 再次放款
	 * 
	 * @param vbsId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> syhResubmitloan(String vbsId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> content = null;
		try {
			params.put("Bid", vbsId);
			logger.info("再次放款的参数:" + vbsId);
			content = VcreditUtils
					.json2Map(HttpRequest.doJson(ApiConstant.SYHReSubmitLoan_URL, VcreditUtils.obj2Json(params)));
			logger.info("再次放款返回参数:" + content.toString());
		} catch (Exception e) {
			throw e;
		}
		return content;
	}
	
	
	/**
	 * 解约
	 * @param vbsId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> syhCancelContract(String vbsId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> content = null;
		try {
			params.put("Bid", vbsId);
			logger.info("解约的参数:{}",vbsId);
			content = VcreditUtils
					.json2Map(HttpRequest.doJson(ApiConstant.SYHCancelContract_URL, VcreditUtils.obj2Json(params)));
			logger.info("解约返回参数:{}",content.toString());
		} catch (Exception e) {
			throw e;
		}
		return content;
	}
	
	
	
	private boolean unfrozenCredit(String custId, BigDecimal loanAmount) {
		CreditQuota creditQuota = loanStatusInfoMapper.getValidCreditQuota(custId);
		Map<String,Object> params = null;
		if (creditQuota != null
				&& creditQuota.getFrozenAccount().compareTo(BigDecimal.ZERO) == 1) {
			params = new HashMap<String,Object>();
			logger.info("解约成功,释放金额" + loanAmount);
			params.put("quotaId", creditQuota.getQuotaId());
			params.put("custId", custId);
			params.put("frozenAmount", creditQuota.getFrozenAccount().subtract(loanAmount));
			logger.info("{}冻结金额：{}",custId,loanStatusInfoMapper.frozenAmount(params));
			return true;
		}
		logger.error("额度表信息不存在,或者冻结金额不对");
		return false;
	}
	
}



