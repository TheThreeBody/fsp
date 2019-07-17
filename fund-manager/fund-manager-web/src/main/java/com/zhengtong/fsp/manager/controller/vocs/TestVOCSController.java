package com.zhengtong.fsp.manager.controller.vocs;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengtong.fsp.commons.core.base.BaseController;
import com.zhengtong.fsp.commons.core.exception.SystemException;
import com.zhengtong.fsp.commons.core.pojo.dtgrid.Pager;
import com.zhengtong.fsp.commons.utils.*;
import com.zhengtong.fsp.manager.pojo.CustEntity;
import com.zhengtong.fsp.manager.pojo.IndentEntity;
import com.zhengtong.fsp.manager.pojo.common.SysConstant;
import com.zhengtong.fsp.manager.service.goboo.impl.GobooConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by dongwen on 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("/vocs/")
public class TestVOCSController extends BaseController {


    @RequestMapping("indentUI.html")
    public String indentUI(Model model, HttpServletRequest request) {
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
            return SysConstant.BACKGROUND_PATH + "/vocs/indent";
        }catch(Exception e)
        {
            throw new SystemException(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "indent.html", method = RequestMethod.POST)
//    @Valid Entity entity , BindingResult bindingResult
    public Object indent( String gridPager) throws Exception {
        Map<String,Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        //设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(),"id DESC");
        Object custId = parameters.get("custId");
        if(parameters.size() <= 0){
            parameters.put("vbsId","1299526");
            parameters.put("productId","esurfing");
        }

        String requestJson = JSON.toJSONString(parameters);
        String result = null;
        String settle = null;
        List<IndentEntity> listSettle = new LinkedList<>();

        if(null != custId) {

            if (StringUtils.isNotBlank(custId.toString())) {
                settle = HttpClientUtil.doPostJson(GobooConstants.CREDIT_MANAGE + GobooConstants.SETTLE_ORDER, requestJson);
                Map resultMap = new HashMap();
                if(settle.indexOf("0") > 0) {
                    System.out.println(settle);

                    resultMap.put("success", Boolean.FALSE);
                    resultMap.put("data", null);
                    resultMap.put("message", "添加失败");
                    return resultMap;
                } else {
                    resultMap.put("success", Boolean.TRUE);
                    resultMap.put("data", null);
                    resultMap.put("message", "添加成功");
                    return resultMap;
                }
            }
        }


//        Map<String,String> requestMap= new ConcurrentHashMap<>();
//        Set<String> keySet = parameters.keySet();
//        for(String key : keySet) {
//            String temp = parameters.get(key).toString();
//            requestMap.put(key,temp);
//        }

//       String result = HttpClientUtil.doPost(GobooConstants.CREDIT_MANAGE + GobooConstants.ORDER_INFO,requestMap);
        result = HttpClientUtil.doPostJson(GobooConstants.CREDIT_MANAGE + GobooConstants.ORDER_INFO,requestJson);
//            String result = testGobooService.queryOrder(parameters);
        List<IndentEntity> list = JSON.parseObject(result, LinkedList.class);
//        List<IndentEntity> list = new LinkedList<>();
//        list.add(indentEntity);
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



    @RequestMapping("custUI.html")
    public String custUI(Model model, HttpServletRequest request) {
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
            return SysConstant.BACKGROUND_PATH + "/vocs/cust";
        }catch(Exception e)
        {
            throw new SystemException(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "cust.html", method = RequestMethod.POST)
    public Object cust(String gridPager) throws Exception {
        Map<String,Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(),"id DESC");
        if(parameters.size() <= 0){
            parameters.put("idCardNo","360111198409010910");
            parameters.put("mobile","15111446259");
            parameters.put("productId","goboo");
        }
        String requestJson = JSON.toJSONString(parameters);

        String result = HttpClientUtil.doPostJson(GobooConstants.CREDIT_MANAGE + GobooConstants.CUST_INFO,requestJson);
        List<CustEntity> list = JSON.parseObject(result, LinkedList.class);

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
