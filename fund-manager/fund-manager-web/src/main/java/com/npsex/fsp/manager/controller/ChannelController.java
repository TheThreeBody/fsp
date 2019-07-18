package com.npsex.fsp.manager.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.npsex.fsp.commons.core.base.BaseController;
import com.npsex.fsp.commons.core.exception.SystemException;
import com.npsex.fsp.commons.core.pojo.dtgrid.Pager;
import com.npsex.fsp.commons.utils.PageUtil;
import com.npsex.fsp.commons.utils.dtgrid.ExportUtils;
import com.npsex.fsp.manager.pojo.ChannelEntity;
import com.npsex.fsp.manager.pojo.common.SysConstant;
import com.npsex.fsp.manager.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by James Wen on 2017/2/10.
 */
@Controller
@Scope("prototype")
@RequestMapping("/channel/")
public class ChannelController extends BaseController {

    @Autowired
    private ChannelService channelService;

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
            return SysConstant.BACKGROUND_PATH + "/channel/list";
        }catch(Exception e)
        {
            throw new SystemException(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "list.html", method = RequestMethod.POST)
    public Object list(String gridPager, HttpServletResponse response) throws Exception {
        Map<String,Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if(parameters.size() < 0){
            parameters.put("name", null);
        }
        //3、判断是否是导出操作
        if (pager.getIsExport()) {
            if (pager.getExportAllData()) {
                //3.1、导出全部数据
                List<ChannelEntity> list = channelService.queryListByPage(parameters);
                ExportUtils.exportAll(response, pager, list);
                return null;
            } else {
                //3.2、导出当前页数据
                return null;
            }
        } else {
            //设置分页，page里面包含了分页信息
            Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(),"id DESC");
            List<ChannelEntity> list = channelService.queryListByPage(parameters);
            parameters.clear();
            parameters.put("isSuccess", Boolean.TRUE);
            parameters.put("nowPage", pager.getNowPage());
            parameters.put("pageSize", pager.getPageSize());
            parameters.put("pageCount", page.getPages());
            parameters.put("recordCount", page.getTotal());
            parameters.put("startRecord", page.getStartRow());
            //列表展示数据
            parameters.put("exhibitDatas", list);
            return parameters;
        }
    }
}
