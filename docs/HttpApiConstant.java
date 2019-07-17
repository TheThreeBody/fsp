package com.zhengtong.fsp.manager.pojo.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by songhuiping on 2017/8/1.
 */
public class HttpApiConstant {


    /**
     * 贷前盖章接口
     */
    public static String CFCA_URL = "http://10.1.12.41:8080/Api/CompoundSeal";

    //正式地址  http://10.1.12.137:8085/esurfing_credit/getSysqsParams
    public static String ESURFING_SYSQS_URL="http://10.1.12.137:8085/esurfing_credit/getSysqsParams";

}
