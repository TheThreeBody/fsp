package com.zhengtong.fsp.manager.controller.baidu_credit;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengtong.fsp.commons.core.base.BaseController;
import com.zhengtong.fsp.commons.core.pojo.dtgrid.Pager;
import com.zhengtong.fsp.manager.pojo.IndentEntity;
import com.zhengtong.fsp.manager.pojo.UserEntity;
import com.zhengtong.fsp.manager.pojo.common.SysConstant;
import com.zhengtong.fsp.manager.service.baidu_credit.QueryDataService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dell on 2017/7/18.
 */
@RequestMapping("/baidu/loanandexception")
@Controller
@Scope("prototype")
public class QueryDataController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(QueryDataController.class);
    
    @Autowired
    private QueryDataService queryDataService;
    @RequestMapping("/loanData.html")
    public String loanData(){
        return SysConstant.BACKGROUND_PATH + "/baidu_credit/LoanData";
    }

    @RequestMapping("/loanDataList.html")
    @ResponseBody
    public Object loanDataList(String gridPager){
    	logger.info("查询放款信息接收到参数-->{}",gridPager);
    	Map<String, Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date applyBegin = null;
        Date applyEnd = null;
        try {
        	if (parameters.get("applyEnd") != null && !"".equals(parameters.get("applyEnd")) ) {
        		applyBegin = sdf.parse((String)parameters.get("applyBegin"));
    			applyEnd = sdf.parse((String)parameters.get("applyEnd"));
    			parameters.put("applyEnd",applyEnd);
    			parameters.put("applyBegin",applyBegin);
        	} else {
        		parameters.put("applyEnd",null);
    			parameters.put("applyBegin",null);
        	}
        	if (parameters.get("loanBegin") == null ||  "".equals(parameters.get("loanBegin"))){//同时为空
        		parameters.put("loanEnd",null);
    			parameters.put("loanBegin",null);
        	} else {
        		Date loanEnd = sdf.parse((String)parameters.get("loanEnd"));
        		Date loanBegin = sdf.parse((String)parameters.get("loanBegin"));
        		parameters.put("loanEnd",loanEnd);
    			parameters.put("loanBegin",loanBegin);
        	}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("时间转化错误");
		}
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), null);
        List<Map<String, Object>> list = queryDataService.queryloanData(parameters);
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

    @RequestMapping("/exceptionData.html")
    public String issueData() {
        return SysConstant.BACKGROUND_PATH + "/baidu_credit/ExceptionData";
    }

    @RequestMapping("/exceptionDataList.html")
    @ResponseBody
    public Object issueDataList(String gridPager) {
    	Map<String, Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        String beginDate = null; // yyyy-MM-dd
        String endDate = null;
        beginDate = ((String)parameters.get("beginDate")).replaceAll("-", "");
        endDate = ((String)parameters.get("endDate")).replaceAll("-", "");
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), "cur_date Desc");
        List<Map<String, Object>> list = queryDataService.queryIssue(beginDate, endDate);
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
    @RequestMapping("/exceptionDataDetail.html")
    @ResponseBody
    public Object issueDataDetail(String gridPager) {
    	Map<String, Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        Long id = Long.parseLong((String)parameters.get("id"));
        String type = (String) parameters.get("type");
        List<Map<String, Object>> list = queryDataService.queryIssueDetail(id, type);
        parameters.clear();
        parameters.put("isSuccess", Boolean.TRUE);
        parameters.put("nowPage", pager.getNowPage());
        parameters.put("pageSize", pager.getPageSize());
        parameters.put("pageCount", 1);
        parameters.put("recordCount", 1);
        parameters.put("startRecord", 1);
        parameters.put("exhibitDatas", list);
        return parameters;
    }
    
    @RequestMapping("/updateExceptionData.html")
    @ResponseBody
    public Object updateIssueData(@RequestParam("data")String param) {
    	logger.info(param);
    	List<Map<String,Object>> list = JSON.parseObject(param, List.class);
    	UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
    	String name = userEntity.getUserName();
    	for(Map<String,Object> per: list){
    		per.put("person",name);
    		per.put("handleTime", new Date());
    		queryDataService.updateIssueData(per);
    		queryDataService.updateAccountStatus(per);
    	}
    	Map<String,String> res = new HashMap<String, String>();
		res.put("code", "000000");
		res.put("msg", "成功");
		return res;
    }
    @RequestMapping("/submitLoan.html")
    @ResponseBody
    public Object submitLoan(@RequestParam("vbs_bid")String vbsBid, 
    		@RequestParam("type") String type, @RequestParam("comment") String comment) {
    	return queryDataService.cancleOrSubmit(type, vbsBid,comment);
    }
}
