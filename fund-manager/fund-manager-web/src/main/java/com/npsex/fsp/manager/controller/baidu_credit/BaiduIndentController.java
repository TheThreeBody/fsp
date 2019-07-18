package com.npsex.fsp.manager.controller.baidu_credit;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.npsex.fsp.commons.core.pojo.dtgrid.Pager;
import com.npsex.fsp.commons.utils.StringUtils;
import com.npsex.fsp.manager.pojo.common.SysConstant;
import com.npsex.fsp.manager.service.baidu_credit.QueryBaiduIndentService;
import com.npsex.fsp.manager.service.baidu_credit.QueryDataService;
import com.npsex.fsp.manager.web.convert.SysDateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dongwen on 2017/7/24.
 */
@Controller
@Scope("prototype")
@RequestMapping("/baidu/indent")
public class BaiduIndentController {

    private static final Logger logger = LoggerFactory.getLogger(BaiduIndentController.class);

    @Autowired
    private QueryDataService queryDataService;

    @Autowired
    private QueryBaiduIndentService queryBaiduIndentService;

    @RequestMapping("/dataUI.html")
    public String queryDataUI() {
        return SysConstant.BACKGROUND_PATH + "/baidu_credit/dataUI";
    }

    @RequestMapping("/data.html")
    @ResponseBody
    public Object queryData(String gridPager) {
        Map<String, Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        Date applyBegin = null;
        Date applyEnd = null;
        Date loanBegin = null;
        Date loanEnd = null;

        String beginDay = " 00:00:00";
        String endDay = " 23:59:59";
        String applyBeginVO = (String)parameters.get("applyBegin");
        String applyEndVO = (String)parameters.get("applyEnd");
        String loanBeginVO = (String)parameters.get("loanBegin");
        String loanEndVO = (String)parameters.get("loanEnd");
//        String applyBeginVO = (String)parameters.get("applyBegin");
//        String applyEndVO = (String)parameters.get("applyEnd");
//        String loanBeginVO = (String)parameters.get("loanBegin");
//        String loanEndVO = (String)parameters.get("loanEnd");
        try {
//            if (StringUtils.isEmpty(parameters.get("applyBegin").toString())){}
//                if (StringUtils.isEmpty(parameters.get("applyEnd").toString())){}
//                    applyEnd = new Date();
//                    applyBegin = DateUtils.addDays(applyEnd, -7);
//                    applyBegin = DateUtils.addDays(applyEnd, -7);
//                if (StringUtils.isEmpty(parameters.get("applyEnd").toString())){}
//                    applyEnd = DateUtils.addDays(applyEnd, 7);
            applyBeginVO = applyBeginVO.replaceAll("/", "-");
            applyBegin = new SysDateFormatter().parse(applyBeginVO + beginDay, null);
//            applyBegin = DateUtils.parseDate(applyBeginVO, "yyyy/mm/dd");
            parameters.put("applyBegin",applyBegin);
            applyEndVO = applyEndVO.replaceAll("/", "-");
            applyEnd = new SysDateFormatter().parse(applyEndVO + endDay, null);
            parameters.put("applyEnd",applyEnd);
            if(StringUtils.isNotEmpty(loanBeginVO) && StringUtils.isNotEmpty(loanEndVO)) {
                loanBeginVO = applyBeginVO.replaceAll("/", "-");
                loanBegin = new SysDateFormatter().parse(loanBeginVO + beginDay, null);
                parameters.put("loanBegin",loanBegin);
                loanEnd = new SysDateFormatter().parse(loanEndVO + endDay, null);
                parameters.put("loanEnd",loanEnd);
            }
        } catch (ParseException e) {
            e.printStackTrace();
//            parameters.remove("applyEnd");
//            parameters.remove("applyBegin");
//            parameters.remove("loanEnd");
//            parameters.remove("loanBegin");
        }
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), null);
        List<Map<String, Object>> list = queryBaiduIndentService.queryBaiduIndent(parameters);
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

    @RequestMapping("/detailUI.html")
    public String queryDetailUI(Model model, String vbsId ,String vbsId1 , String bdLoanId) {
        Map<String, Object> queryParam = new ConcurrentHashMap<>();
        if(StringUtils.isNotBlank(vbsId)) {
            queryParam.put("vbsId", vbsId);
            model.addAttribute("vbsId", vbsId);
        }
        if( StringUtils.isNotBlank(bdLoanId)) {
            queryParam.put("bdLoanId", bdLoanId);
        }
        if(StringUtils.isNotBlank(vbsId1) ) {
            queryParam.put("vbsId", vbsId1);
        }
        List<Map<String, Object>> list = queryBaiduIndentService.queryBaiduIndentDetail(queryParam);
        if(list.size() > 0) {
            Map indentInfo = list.get(0);
//            String datR = indentInfo.get("Day_Rate").toString();
//            BigDecimal bD = new BigDecimal(datR).setScale(3);
//            indentInfo.put("Day_Rate",bD);
//            String fRate = indentInfo.get("Formalities_Rate").toString();
//            BigDecimal bF = new BigDecimal(fRate).setScale((fRate.length() - 4),BigDecimal.ROUND_UNNECESSARY);
//            indentInfo.put("Formalities_Rate",bF);
            if( StringUtils.isNotBlank(vbsId) || StringUtils.isNotBlank(vbsId1) || StringUtils.isNotBlank(bdLoanId)) {
                model.addAttribute("indentInfo", indentInfo);
            }
        }

        return SysConstant.BACKGROUND_PATH + "/baidu_credit/detailUI";
    }

//    @RequestMapping("/file/detail.html")
//    @ResponseBody
//    public Object queryFileDetail(String gridPager) {
//        Map<String, Object> parameters = null;
//        Pager pager = JSON.parseObject(gridPager, Pager.class);
//        parameters = pager.getParameters();
//
//        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), null);
//
//        parameters.clear();
//        parameters.put("isSuccess", Boolean.TRUE);
//        parameters.put("nowPage", pager.getNowPage());
//        parameters.put("pageSize", pager.getPageSize());
//        parameters.put("pageCount", page.getPages());
//        parameters.put("recordCount", page.getTotal());
//        parameters.put("startRecord", page.getStartRow());
////        parameters.put("exhibitDatas", list);
//        return parameters;
//    }

    @RequestMapping("/file.html")
    @ResponseBody
    public Object queryFinanceFile(String gridPager, @RequestParam("vbsId") String vbsId) {
        Map<String, Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        parameters.clear();
        if(StringUtils.isEmpty(vbsId)) {
            vbsId = (String)parameters.get("vbsId");
        }
        parameters.put("vbsId",vbsId);

        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), null);
        List<Map<String, Object>> list = queryBaiduIndentService.queryBaiduFinancialFile(parameters);
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
