package com.npsex.fsp.manager.service.goboo.impl;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;






import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GobooHttpClientUtil {
	
	private static Logger logger = LoggerFactory.getLogger(GobooHttpClientUtil.class);
	/**
	 * 
	 * 作者：董文
	 * 创建时间：2016年8月3日下午3:24:38
	 * 版本：1.0.0
	 */
	 public static String requestByPostMethod(String jsonStr, String pushName) {
		 
		 String resultStr = "{\"message\":\"not match : - \u001A\",\"type\":\"failure\"}";
		 
		 //测试
//		 if(null == jsonStr || "".equals(jsonStr)) {
//			 jsonStr = "{\"deptName\":\"部门名称001\",\"agentEmail\":\"经办人邮件001\",\"eplName\":\"项目名称001\","
//		                + "\"agentTel\":\"经办人联系001\",\"companyName\":\"公司名称001\",\"eplCode\":\"project-10001\","
//		                + "\"agentName\":\"招标经办人001\",\"agentMobile\":\"经办人手机001\",\"bidForm\":\"采购方式001\","
//		                + "\"createTime\":\"20160726145257\",\"applicationCode\":\"application-10001\",\"isFixed\":\"0\"}";
//		 }
		 
		 CloseableHttpClient httpClient = HttpClients.createDefault();
		 HttpPost httpPost = new HttpPost(GobooConstants.CREDIT_MANAGE+ pushName);

		 List<NameValuePair> list = new ArrayList<NameValuePair>();
		 list.add(new BasicNameValuePair("jsonStr",jsonStr));
		// url格式编码
         UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
		
			httpPost.setEntity(uefEntity);
//			httpGet.setParams((HttpParams) list);
	         // 执行请求
	         
				CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			try{
				HttpEntity responseEntity = httpResponse.getEntity();
				if (null != responseEntity) {
                    System.err.println("响应状态码:" + httpResponse.getStatusLine());
                    System.err.println("-------------------------------------------------------");
                    resultStr = EntityUtils.toString(responseEntity);
                    System.err.println("返回对象内容：" + resultStr);
                    System.err.println("-------------------------------------------------------");
                    
                }
            } finally {
                httpResponse.close();
            }
         
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
		            closeHttpClient(httpClient);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		}
		//返回String结果 交给service层解析
		return resultStr;
	 }
	 
	 private static void closeHttpClient(CloseableHttpClient client) throws IOException {
	        if (client != null) {
	            client.close();
	        }
	    }
	 
}
