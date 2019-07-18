package com.npsex.fsp.manager.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npsex.fsp.manager.mapper.vocs.PersonalInformationJobMapper;
import com.npsex.fsp.manager.pojo.job.grxxsqs.GrxxsqsGenerStatus;
import com.npsex.fsp.manager.pojo.job.grxxsqs.GrxxsqsParams;
import com.npsex.fsp.manager.pojo.quartz.CommonPersonalInfo;
import com.npsex.fsp.manager.pojo.quartz.UUIDGenerator;
import com.npsex.fsp.manager.service.PersonalInformationJobService;

@Service
public class PersonalInformationJobServiceImpl implements PersonalInformationJobService {

	public Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PersonalInformationJobMapper piJobMapper;

	@Override
	public List<CommonPersonalInfo> queryPersonalInfo(String partitionDbKeys) {
		List<CommonPersonalInfo> list = null;
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			params.put("startTime", getBeforeStartDay(new Date()));
			params.put("endTime", getBeforeEndDay(new Date()));
		} catch (ParseException e1) {
			logger.error("日期转换异常");
		}
		
		try {
			switch (partitionDbKeys) {
			case "dataSourceGoboo":
				list = piJobMapper.queryGobooPersonalInfo(params);
				break;
			case "dataSourceHebao":
				list = piJobMapper.queryHebaoPersonalInfo(params);
				break;
			case "dataSourceEsurfingCredit":
				list = piJobMapper.queryEsurfingCreditPersonalInfo(params);
				break;
			case "dataSourceEsurfing":
				list = piJobMapper.queryEsurfingPersonalInfo(params);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("{}分库发生异常", partitionDbKeys);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int batchSavePersonInfo(List<CommonPersonalInfo> result) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (CommonPersonalInfo commonPersonalInfo : result) {
			GrxxsqsGenerStatus status = new GrxxsqsGenerStatus();
			GrxxsqsParams params = new GrxxsqsParams();
			params.setCreateTime(new Date());// 创建时间
			params.setCreditTime(
					commonPersonalInfo.getCreditTime() == null ? null : sdf.format(commonPersonalInfo.getCreditTime()));// 授信时间
			params.setCustId(commonPersonalInfo.getCustId());// 用户id
			params.setCustIdNo(commonPersonalInfo.getCustIdNo());// 用户身份证
			params.setCustMobile(commonPersonalInfo.getCustMobile());// 用户手机号
			params.setCustName(commonPersonalInfo.getCustName());// 用户姓名
			params.setGrxxsqsUuid(UUIDGenerator.getUUID());// 随机的uuid
			params.setProductType(commonPersonalInfo.getProductType().byteValue());// 产品类型
			status.setGrxxsqsUuid(params.getGrxxsqsUuid());//
			status.setCreateTime(new Date());// 创建时间
			piJobMapper.insertGrxxsqsParams(params);
			piJobMapper.insertGrxxsqsGenerStatus(status);
		}
		return 1;
	}
	
	
	private static Date getBeforeEndDay(Date date) throws ParseException {  
		SimpleDateFormat sdf1  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return sdf1.parse(sdf.format(date)+" 23:59:59");  
    }  
	
	private static Date getBeforeStartDay(Date date) throws ParseException {  
		SimpleDateFormat sdf1  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return sdf1.parse(sdf.format(date)+" 00:00:00");  
    }  
}
