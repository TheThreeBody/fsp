package com.zhengtong.fsp.manager.service.goboo.impl;

import java.util.regex.Pattern;
/**
 * 
 * 作者：董文
 * 创建时间：2016年8月4日下午7:53:35
 * 版本：1.0.0
 */
public class GobooConstants {

	//接口对应URL头地址
	public static final String CREDIT_MANAGE = "http://10.100.22.58:8084/credit_manage";
	
	
	//接口后缀对应业务地址
	public static final String ORDER_INFO = "/order/queryOrderInfo.json";
	
	public static final String CUST_INFO = "/custInfo/queryCustInfo.json";

	public static final String SETTLE_ORDER = "/order/settleOrder.do";
	

	
	//换行符正则Model
	public static final Pattern  CHANGE_LINE = Pattern.compile("\n");
	//替换换行符后用于匹配oti_fund_loan_successful_case表中 content字段 的jsonStr字符串的正则表达式Model;
	public static final Pattern FUND_SUCCESSFUL_CASE_CONTENT_REGEX = 
			Pattern.compile("\\\"content\\\"\\:(\\\".*\\\")\\\"\\,", Pattern.CASE_INSENSITIVE);
	//逗号正则Model
	public static final Pattern COMMA = Pattern.compile("\\,");
	//处理content字符串正则
	public static final Pattern CONTENT_HEAD_REGEX = 
			Pattern.compile("\\\"content\\\"\\:\\\"", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern CONTENT_TAIL_REGEX = 
			Pattern.compile("\\\"\\,", Pattern.CASE_INSENSITIVE);
}
