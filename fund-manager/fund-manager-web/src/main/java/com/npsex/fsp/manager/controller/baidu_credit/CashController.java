package com.npsex.fsp.manager.controller.baidu_credit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.npsex.fsp.commons.core.base.BaseController;
import com.npsex.fsp.commons.core.exception.SystemException;
import com.npsex.fsp.commons.core.pojo.dtgrid.Pager;
import com.npsex.fsp.manager.mapper.baidu_credit.CashMapper;
import com.npsex.fsp.manager.pojo.CashEntity;
import com.npsex.fsp.manager.pojo.common.SysConstant;

@Controller
@Scope("prototype")
@RequestMapping("/baidu/cash/")
public class CashController  extends BaseController{
	@Autowired
	private CashMapper cashMapper;
	@RequestMapping(   value = "cash.html")
	public String cash() {
		
		try
		{
			return SysConstant.BACKGROUND_PATH + "/baidu_credit/cashList";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping(value = "cashList.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		String param="";
		 for(Map.Entry<String, Object> entry:parameters.entrySet()){  
	           if( (entry.getValue()!=null) && (!"".equals(entry.getValue().toString()))){
	        	   param=entry.getValue().toString();
	        	   break;
	           }
		 }
		 if("".equals(param)){
			 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			Date date= new Date();
			Date date1= new Date();
			    date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
			 Calendar   calendar=     Calendar.getInstance();
			 calendar.setTime(date);
			  calendar.set(Calendar.MILLISECOND, 0);
			 calendar.add(Calendar.DATE, -6);
			 parameters.put("beginTime", sdf.format(calendar.getTime()));
			 parameters.put("endTime",sdf.format(date1) );
		 }
		// 设置分页，page里面包含了分页信息
		//BaiduCreditEntity baiduCreditEntity=new BaiduCreditEntity();
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), null);
		List<CashEntity> list = cashMapper.queryCashList(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}
	@RequestMapping(value = "cashDetail.html", method = RequestMethod.POST)
	@ResponseBody
	public Object cashDetail(HttpServletRequest req) throws Exception{
		String transactionApplyId=      req.getParameter("transactionApplyId");
		CashEntity cashEntity=	cashMapper.findCashDetail(transactionApplyId);
		return cashEntity; 
	}
	@RequestMapping(value = "solution.html", method = RequestMethod.POST)
	@ResponseBody
	public String solution(HttpServletRequest req) throws Exception{
		String transactionApplyId=      req.getParameter("transactionApplyId");
		String solutionException=      req.getParameter("solutionException");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("transactionApplyId", transactionApplyId);
		map.put("solutionException", solutionException);
	      cashMapper.solutionException(map);
	      return "success";
	}
	


}
