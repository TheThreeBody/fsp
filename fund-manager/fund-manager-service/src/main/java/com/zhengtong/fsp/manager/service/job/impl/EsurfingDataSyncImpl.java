package com.zhengtong.fsp.manager.service.job.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhengtong.fsp.commons.utils.DateTimeUtils;
import com.zhengtong.fsp.commons.utils.HttpClientUtil;
import com.zhengtong.fsp.commons.utils.JsonUtils;
import com.zhengtong.fsp.manager.mapper.job.syqrs.SyqrsGenerStatusMapper;
import com.zhengtong.fsp.manager.mapper.job.syqrs.SyqrsParamsMapper;
import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsGenerStatusEntity;
import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsParamsEntity;
import com.zhengtong.fsp.manager.pojo.common.HttpApiConstant;
import com.zhengtong.fsp.manager.service.job.EsurfingDataSync;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xiepeng on 2017/7/25.
 */
@Service
public class EsurfingDataSyncImpl implements EsurfingDataSync {

    private static final Logger logger = LogManager.getLogger(EsurfingDataSyncImpl.class);

    @Autowired
    private SyqrsParamsMapper syqrsParamsMapper;

    @Autowired
    private SyqrsGenerStatusMapper syqrsGenerStatusMapper;

    //    担保费率
    private static final String guarantee_fee_rate = "5";

    //    担保费
    private static final byte  gurantee_fee = 10;

    //    滞纳金
    private static final String overdue_payment = "0.002";

    //    还款日 x日
    private static final int repayment_date = 12;

    //    贷款方1
    private static final int credit_side = 1;

    //    担保方1
    private static final int guarantor = 1;


    public boolean addSyqrsData(Date reportTime) throws Exception{
        List<SyqrsParamsEntity> syqresparams = getParamsFormEsrufingCredit(reportTime);
        if (syqresparams.size() > 0) {
            for (int i = 0; i < syqresparams.size(); i++) {
                //构造param
                try {
                    String uuid = UUID.randomUUID().toString();
                    String finalRepaymentDate = "";    //最后还款时间
                    if (null != syqresparams.get(i).getConsumeTime()) {
                         Date consumeTime = DateTimeUtils.SDF.parse(syqresparams.get(i).getConsumeTime());
                        if (consumeTime.getDay() >= 27) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(DateTimeUtils.addMonthsDateTime(consumeTime, 2));
                            calendar.set(calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH), 12);
                            finalRepaymentDate = DateTimeUtils.getChsDate(
                                    calendar.getTime());
                        } else {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(DateTimeUtils.addMonthsDateTime(consumeTime, 1));
                            calendar.set(calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH), 12);
                            finalRepaymentDate = DateTimeUtils.getChsDate(
                                    calendar.getTime());
                        }
                    }
                    syqresparams.get(i).setSyqrsUuid(uuid);
                    syqresparams.get(i).setFinalRepaymentDate(finalRepaymentDate);
                    syqresparams.get(i).setGuaranteeFeeRate(guarantee_fee_rate);
                    syqresparams.get(i).setGuranteeFee(gurantee_fee);
                    syqresparams.get(i).setOverduePayment(overdue_payment);
                    syqresparams.get(i).setRepaymentDate((byte) repayment_date);
                    syqresparams.get(i).setCreditSide((byte) credit_side);
                    syqresparams.get(i).setGuarantor((byte) guarantor);
                    syqrsParamsMapper.insertSelective(syqresparams.get(i));

                    //构造生成授权书状态表
                    SyqrsGenerStatusEntity generStatusEntity = new SyqrsGenerStatusEntity();
                    generStatusEntity.setSyqrsUuid(uuid);
                    generStatusEntity.setGenerateStatus((byte) 2);
                    syqrsGenerStatusMapper.insertSelective(generStatusEntity);

                } catch (Exception e) {
                    logger.error(e.toString() + " custID=" + syqresparams.get(i).getCustId());
                    throw e;
                }

            }
        } else {
            throw new Exception("无当日新增消费");
        }
        return true;
    }

    private List<SyqrsParamsEntity> getParamsFormEsrufingCredit(Date reportTime) throws Exception{
        Map<String,Object> header=new HashMap<>();
        header.put("source","vcredit");
        String requestResult=HttpClientUtil.doPostJson(HttpApiConstant.ESURFING_SYSQS_URL,"\""+DateTimeUtils.SDF.format(reportTime)+"\"",header);
        Map<String,Object> requestResultMap= JsonUtils.Json2Map(requestResult);
        if (requestResultMap.get("result").equals(0)&&requestResultMap.get("content")!=null){
            List<SyqrsParamsEntity> requestEntity=JsonUtils.Json2Obj(JsonUtils.Obj2Json(requestResultMap.get("content")),new TypeReference<List<SyqrsParamsEntity>>(){});
            return  requestEntity;
        }
        return new ArrayList<SyqrsParamsEntity>();
    }
}
