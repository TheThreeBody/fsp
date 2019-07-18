package com.npsex.fsp.commons.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by dongwen on 2017/7/21.
 *脱敏后端
 */
public class DesensitizationUtils {

    private static final Logger logger = LogManager.getLogger(DesensitizationUtils.class);

    public static  String nameDesensitization(String oldName) throws Exception{

        StringBuffer resultName = new StringBuffer(oldName);
        int lenth = resultName.length();
        if(lenth>0 ||lenth<2) {
            resultName.replace(1, 1, "*");
        }else if(lenth > 2) {
            resultName.replace(1, 2, "*");
        }

        logger.info("脱敏" + resultName);

        return resultName.toString();
    }

    public static  String mobileDesensitization(String oldMobile) throws Exception{
        StringBuffer resultMobile = new StringBuffer(oldMobile);
        int lenth = resultMobile.length();
            resultMobile.replace(0, 2, "*");
            resultMobile.replace(lenth-3, lenth, "*");

        logger.info("脱敏" + resultMobile);

        return resultMobile.toString();
    }

    public static  String idCardDesensitization(String oldId) throws Exception{
        StringBuffer resultId = new StringBuffer(oldId);
        int lenth = resultId.length();
        resultId.replace(0, 3, "*");
        resultId.replace(lenth-4, lenth, "*");

        logger.info("脱敏" + resultId);

        return resultId.toString();
    }

    public static  String bankCardDesensitization(String oldBank) throws Exception{
        StringBuffer resultBank = new StringBuffer(oldBank);
        int lenth = resultBank.length();
        resultBank.replace(0, 3, "*");
        resultBank.replace(lenth-4, lenth, "*");


        return resultBank.toString();
    }



}
