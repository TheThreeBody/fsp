package com.zhengtong.fsp.manager.controller.esurfing;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengtong.fsp.commons.core.base.BaseController;
import com.zhengtong.fsp.commons.core.exception.AjaxException;
import com.zhengtong.fsp.commons.core.exception.SystemException;
import com.zhengtong.fsp.commons.core.pojo.dtgrid.Pager;
import com.zhengtong.fsp.commons.utils.HttpClientUtil;
import com.zhengtong.fsp.commons.utils.JsonUtils;
import com.zhengtong.fsp.manager.pojo.FrozenEntity;
import com.zhengtong.fsp.manager.pojo.IndentEntity;
import com.zhengtong.fsp.manager.pojo.common.SysConstant;
import com.zhengtong.fsp.manager.service.esurfing.TestEsurfingService;
import com.zhengtong.fsp.manager.support.shiro.ShiroAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dongwen on 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("/esurfing")
public class TestEsurfingController extends BaseController {

    @Autowired
    private TestEsurfingService testEsurfingService;


    @RequestMapping("/listUI.html")
    public String listUI() {
        try
        {
            return SysConstant.BACKGROUND_PATH + "/esurfing/list";
        }catch(Exception e)
        {
            throw new SystemException(e);
        }
    }

    /**
     * ajax分页动态加载模式
     *
     * @param gridPager
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
            parameters.put("vbsId", "1");
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), "name ASC");
        List<IndentEntity> list = testEsurfingService.queryEsurfingOrderInfo(parameters);
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

    @RequestMapping("/frozenUI.html")
    public String frozenUI(Model model, HttpServletRequest request, Long custId ,Long vbsId) {
        try
        {
            if(custId == null) {
                custId = 0l;
            }
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("custId",custId);
            parameter.put("vbsId",vbsId);
//            List<FrozenEntity> list = testEsurfingService.queryEsurfingFrozenAmount(parameter);
//            PageUtil page = new PageUtil();
//            page.setPageNum(1);
//            page.setPageSize(1);
//            model.addAttribute("page", page);
//            model.addAttribute("frozenEntity", list.get(0));
            model.addAttribute("custId",custId);
            model.addAttribute("vbsId",vbsId);
            return SysConstant.BACKGROUND_PATH + "/esurfing/frozen";
        }catch(Exception e)
        {
            throw new AjaxException(e);
        }
    }

    @RequestMapping("/frozen.html")
    @ResponseBody
    public Object frozen(String gridPager ,@RequestParam("custId")Long custId,@RequestParam("vbsId")Long vbsId) throws Exception{
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if(parameters.containsKey("vbsId")){
            if("0".equals(parameters.get("vbsId"))) {
                parameters.put("vbsId", vbsId);
            }
        }else {
            parameters.put("vbsId", vbsId);
        }
        if(!(custId == 0l)) {
                parameters.put("custId", custId);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize(), "credit_Id ASC");
        List<FrozenEntity> list = testEsurfingService.queryEsurfingFrozenAmount(parameters);
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

    @RequestMapping("/queryVbsId.html")
    @ResponseBody
    public String queryVBSID(@RequestParam("creditId")Long creditId ,@RequestParam("orderId")Long orderId) {
        Map<String, Object> mapOrderInfo = new ConcurrentHashMap<>();
        String vbsId = "false";
        mapOrderInfo.put("orderId",orderId);
        mapOrderInfo.put("creditId",creditId);
        List<Map<String,Object>> list = testEsurfingService.queryEsurfingVBSID(mapOrderInfo);

        if(list.size() == 1) {
            vbsId =  list.get(0).get("vbsId").toString();
        }
        return vbsId;
    }
    @RequestMapping("/isClearLoan.html")
    @ResponseBody
    public String isClearLoan(@RequestParam("vbsId")Long vbsId){
        String result;
//        result = HttpClientUtil.doPostJson("http://10.138.60.68:20000/V3SBaseService/PostLoan/QueryBusinessStatusAddXYH","{"BusinessIDList":[" + 5077176 + "]}");
        result = HttpClientUtil.doPostJson("http://10.1.12.51:10001/V3SBaseService/PostLoan/QueryBusinessStatusAddXYH","{\"BusinessIDList\":[" + vbsId + "]}");
        try {
            Map resultMap = JsonUtils.Json2Map(result);
            result = resultMap.get("Content").toString();
            if(null == result) {
                return result;
            }
            result = result.replace("[","");
            result = result.replace("]","");
            Map conMap = JsonUtils.Json2Map(result);
            result = conMap.get("IsClearLoan").toString();
        }catch (IOException ioe) {
            throw new AjaxException("the After Loan Api is broken！");
        }

        return result;
    }

    @RequestMapping("/unfrozen.html")
    @ResponseBody
    public Object unfrozen(@RequestParam("creditId")Long creditId,@RequestParam("custId")Long custId,
                           @RequestParam("orderId")Long orderId
//            ,@RequestParam("orderStatus")String orderStatus
    ) {
        Map<String, Object> mapFrozenCustCredit = new ConcurrentHashMap<>();
        Map<String, Object> mapFrozenOrder = new ConcurrentHashMap<>();
        Map<String, Object> mapOrderInfo = new ConcurrentHashMap<>();
        String surfingMsg = ShiroAuthenticationManager.getUserAccountName() + ": update at time : " + new Date().toString();
        try {

//            mapOrderInfo.put("custId",custId);
            mapOrderInfo.put("orderId",orderId);

            mapFrozenCustCredit.put("creditId",creditId);
            mapFrozenOrder.put("surfingMsg",surfingMsg);

            mapFrozenOrder.put("orderId",orderId);

            int result1 = testEsurfingService.updateFrozenCustCredit(mapFrozenCustCredit);

            List<IndentEntity> list = testEsurfingService.queryEsurfingOrderInfo(mapOrderInfo);
            if("4".equals(list.get(0).getOrderStatus())) {
//          if("4".equals(orderStatus)){
                int result2 = testEsurfingService.updateFrozenOrder(mapFrozenOrder);
                logger.info(result2);

            }

        }catch(Exception e)
        {
            throw new AjaxException(e);
        }
        return mapFrozenCustCredit;
    }

}
