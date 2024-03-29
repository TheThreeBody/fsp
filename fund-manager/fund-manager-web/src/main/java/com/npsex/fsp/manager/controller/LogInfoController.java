package com.npsex.fsp.manager.controller;

import java.util.List;
import java.util.Map;

import com.npsex.fsp.manager.pojo.common.SysConstant;
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
import com.npsex.fsp.manager.pojo.LogInfoEntity;
import com.npsex.fsp.manager.service.LogInfoService;
import com.npsex.fsp.commons.core.pojo.dtgrid.Pager;

@Controller
@Scope("prototype")
@RequestMapping(value = "/logInfo/")
public class LogInfoController extends BaseController {

	@Autowired
	private LogInfoService logInfoService;

	@RequestMapping("listUI.html")
	public String listUI() {
		try
		{
			return SysConstant.BACKGROUND_PATH + "/loginfo/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}

	/**
	 * ajax分页动态加载模式
	 * 
	 * @param dtGridPager
	 *            Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("accountName", null);
		}
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "l_create_time DESC");
		List<LogInfoEntity> list = logInfoService.queryListByPage(parameters);
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

}
