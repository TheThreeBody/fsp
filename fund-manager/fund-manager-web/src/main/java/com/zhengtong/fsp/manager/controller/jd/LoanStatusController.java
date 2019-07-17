package com.zhengtong.fsp.manager.controller.jd;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengtong.fsp.commons.core.base.BaseController;
import com.zhengtong.fsp.commons.core.exception.SystemException;
import com.zhengtong.fsp.commons.core.pojo.dtgrid.Pager;
import com.zhengtong.fsp.commons.utils.dtgrid.ExportUtils;
import com.zhengtong.fsp.manager.pojo.common.SysConstant;
import com.zhengtong.fsp.manager.pojo.jd.CommonResponse;
import com.zhengtong.fsp.manager.pojo.jd.LoanStatusInfo;
import com.zhengtong.fsp.manager.service.jd.LoanStatusService;
import com.zhengtong.fsp.manager.support.shiro.ShiroAuthenticationManager;

@RequestMapping("/jd/loan/")
@Controller
public class LoanStatusController extends BaseController {

	@Autowired
	private LoanStatusService loanStatusService;

	@RequestMapping("/queryLoanStatus.html")
	public String listUI() {
		try {
			return SysConstant.BACKGROUND_PATH + "/jd/loanstatus";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager, HttpServletResponse response) throws Exception {
		logger.info("分页信息：{}", gridPager);
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();

		// 3、判断是否是导出操作
		if (pager.getIsExport()) {
			List<LoanStatusInfo> list = loanStatusService.queryLoanStatusList(parameters);
			ExportUtils.exportAll(response, pager, list);
			if (pager.getExportAllData()) {
				return null;
			} else {
				// 3.2、导出当前页数据
				ExportUtils.export(response, pager);
				return null;
			}
		} else {
			// 设置分页，page里面包含了分页信息
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), "");
			List<LoanStatusInfo> list = loanStatusService.queryLoanStatusList(parameters);
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
	
	@RequestMapping("/againLoan.html")
	@ResponseBody
	public CommonResponse againLoan(String vbsId) {
		try {
			logger.info("再次提交放款,业务号为{}",vbsId);
			return loanStatusService.againLoan(vbsId,ShiroAuthenticationManager.getUserAccountName());
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("/surrender.html")
	@ResponseBody
	public CommonResponse surrender(String vbsId,String remark) {
		try {
			logger.info("解约,业务号为{},备注为",vbsId);
			return loanStatusService.surrender(vbsId,remark,ShiroAuthenticationManager.getUserAccountName());
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

}
