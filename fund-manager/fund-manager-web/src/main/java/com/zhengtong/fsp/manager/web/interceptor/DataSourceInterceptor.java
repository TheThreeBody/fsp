package com.zhengtong.fsp.manager.web.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.zhengtong.fsp.commons.core.datasource.DatabaseContextHolder;

/**
 * Created by dongwen on 2017/7/4.
 */
@Component
@Aspect
public class DataSourceInterceptor {


    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.*.*(..))")
    public void controllerPoint() {
    }

//    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.vcos.*.*(..))")
//    public void vocsPoint() {
//    }

    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.goboo.*.*(..))")
    public void gobooPoint() {
    }

    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.hebao.*.*(..))")
    public void hebaoPoint() {
    }

    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.esurfing.*.*(..))")
    public void esurfingPoint() {
    }

    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.esurfing_credit.*.*(..))")
    public void esurfingCreditPoint() {
    }

    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.baidu_credit.*.*(..))")
    public void baiduCreditPoint(){
    }
    
    @Pointcut("execution(* com.zhengtong.fsp.manager.controller.jd.*.*(..))")
    public void jdPoint(){
    }
    
    /**
     * 后台管理系统库
     * @param vocsJp
     */
//    @Before(value = "vocsPoint()")
    @After(value = "controllerPoint()")
    public void setdataSourceVOCS(JoinPoint vocsJp) {
        DatabaseContextHolder.setCustomerType("dataSourceVOCS");
    }


}