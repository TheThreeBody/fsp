/**
 * 
 */
package com.npsex.fsp.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.npsex.fsp.manager.pojo.common.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.npsex.fsp.commons.core.base.BaseController;
import com.npsex.fsp.commons.core.exception.AjaxException;
import com.npsex.fsp.manager.pojo.UserSessionEntity;
import com.npsex.fsp.manager.service.impl.UserSessionServiceImpl;
import com.npsex.fsp.commons.utils.PageUtil;

/**
 * @ClassName UserSessionController
 * @Description TODO
 *
 * @author Dongwen
 * @data 2016年12月14日 下午6:02:13
 */
@Controller
@Scope("prototype")
@RequestMapping("/session/")
public class UserSessionController extends BaseController{

	
	@Autowired
	private UserSessionServiceImpl userSessionService;
	
	
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return SysConstant.BACKGROUND_PATH + "/user/sessionList";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * ajax分页动态加载模式
	 * @param dtGridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html")
	@ResponseBody
	public Object list(String gridPager, HttpServletResponse response) throws Exception{
		List<UserSessionEntity> list = userSessionService.getAllUser();
		return list;
	}
	
	
	/**
	 * 踢出用户
	 * @param id	用户id
	 * @throws Exception
	 */
	@RequestMapping(value = "/info.html")
	public String info(Model model, String sessionId) throws Exception
	{
		UserSessionEntity userSessionEntity = userSessionService.getSession(sessionId);
		model.addAttribute("userSessionEntity", userSessionEntity);
		return SysConstant.BACKGROUND_PATH + "/user/sessionInfo";
	}
	
	
	/**
	 * 踢出用户
	 * @param id	用户id
	 * @throws Exception
	 */
	@RequestMapping(value = "/kickout.html", method = RequestMethod.POST)
	@ResponseBody
	public Object kickout(String ids) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			boolean flag = userSessionService.kickoutUser(ids);
			if(flag)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "用户已被踢出");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "踢出用户失败");
			}
		}catch(Exception e)
		{
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "踢出用户失败");
			throw new AjaxException(e);
		}
		return map;
	}
	
}
