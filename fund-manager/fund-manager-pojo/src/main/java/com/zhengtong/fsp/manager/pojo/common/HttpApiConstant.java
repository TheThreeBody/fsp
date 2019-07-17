package com.zhengtong.fsp.manager.pojo.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by songhuiping on 2017/8/1.
 */
public class HttpApiConstant {


    /**
     * 贷前盖章接口
     */
    @Value("${cfca.url}")
    public static String CFCA_URL;
//    public static String CFCA_URL = "http://10.138.60.115:8089/Api/CompoundSeal";


    @Value("${esurfing.sysqs.url}")
    public static String ESURFING_SYSQS_URL;
    //    public static String ESURFING_SYSQS_URL="http://localhost:8080/esurfing_credit_PCR017/getSysqsParams";

}
