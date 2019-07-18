package com.npsex.fsp.commons.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ReflectionUtils;

/**

 * @author dell
 *
 */
@SuppressWarnings("deprecation")
public class HttpRequest {
	private static String defaultCharSet = "UTF-8";
	@SuppressWarnings("resource")
	public static String doJson(String url, String json,String charset,Header ... header) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity s = new StringEntity(json.toString(), charset);
		s.setContentEncoding(charset);
		s.setContentType("application/json;charset=UTF-8");// 发送json数据需要设置contentType
		for(Header h:header){
			post.addHeader(h);
		}
		post.setEntity(s);
		HttpResponse res = client.execute(post);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return EntityUtils.toString(res.getEntity(),charset);
		}
		throw new RuntimeException("http request result code:"
				+ res.getStatusLine().getStatusCode());
	}
	
	public static String doJson(String url, String json) throws Exception{
		return doJson(url, json, defaultCharSet);
	}
	
	public static String doText(String url, String text,String charset) throws Exception{
		@SuppressWarnings("resource")
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity s = new StringEntity(text,charset);
		s.setContentEncoding(charset);
		post.setEntity(s);
		HttpResponse res = client.execute(post);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return EntityUtils.toString(res.getEntity());
		}
		throw new RuntimeException("http request result code:"
				+ res.getStatusLine().getStatusCode());
	}
	
	public static String doPostByJson(String url, String obj) throws IOException {
		@SuppressWarnings("resource")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);

		method.addHeader("Content-type", "application/json; charset=utf-8");
		method.setHeader("Accept", "application/json");
		method.setEntity(new StringEntity(VcreditUtils.obj2Json(obj), Charset.forName("UTF-8")));
		HttpResponse response = httpClient.execute(method);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {

		}

		return EntityUtils.toString(response.getEntity(), "UTF-8");
	}
	
	/***
	 * 通过JSON方式提交
	 * 
	 * @param url
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static String doPostByJson(String url, Object obj) throws IOException {
		@SuppressWarnings("resource")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);

		method.addHeader("Content-type", "application/json; charset=utf-8");
		method.setHeader("Accept", "application/json");
		method.setEntity(new StringEntity(VcreditUtils.obj2Json(obj), Charset.forName("UTF-8")));
		HttpResponse response = httpClient.execute(method);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {

		}

		return EntityUtils.toString(response.getEntity(), "UTF-8");
	}
	
	public static String doText(String url, String text) throws Exception{
		return doText(url, text,defaultCharSet);
	}
	
	public static String doPost(String url,String charset,String... param) throws Exception {
		if (param != null && param.length % 2 != 0)
			throw new Exception("param count excepiton");
		CloseableHttpResponse response = null;
		@SuppressWarnings("resource")
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (param != null)
			for (int i = 0; i < param.length; i = i + 2) {
				formparams.add(new BasicNameValuePair(param[i], param[i + 1]));
			}
		//UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		post.setEntity(new UrlEncodedFormEntity(formparams, charset));
		response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return EntityUtils.toString(response.getEntity());
		}
		throw new RuntimeException("http request result code:"
				+ response.getStatusLine().getStatusCode());
	}
	
	public static String doPost(String url,String... param) throws Exception {
		return doPost(url, defaultCharSet, param);
	}
	
	public static String doGet(String url,String charset,String... param) throws Exception {
		if (param != null && param.length % 2 != 0)
			throw new Exception("param count excepiton");
		CloseableHttpResponse response = null;
		@SuppressWarnings("resource")
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		if (param != null)
			for (int i = 0; i < param.length; i = i + 2) {
				BasicHttpParams httpParam = new BasicHttpParams();
				httpParam.setParameter(param[i], param[i + 1]);
				get.setParams(httpParam);
			}
		response = client.execute(get);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return EntityUtils.toString(response.getEntity());
		}
		throw new RuntimeException("http request result code:"
				+ response.getStatusLine().getStatusCode());
	}

	public static String doPost(String url,String charset, Object obj) throws Exception {
		CloseableHttpResponse response = null;
		@SuppressWarnings("resource")
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> formparams = postParam(obj);
		post.setEntity(new UrlEncodedFormEntity(formparams, charset));
		response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return EntityUtils.toString(response.getEntity());
		}
		throw new RuntimeException("http request result code:"
				+ response.getStatusLine().getStatusCode());
	}

	public static String doPost(String url, Object obj) throws Exception {
		return doPost(url, defaultCharSet, obj);
	}
	
	public static String doGet(String url, Object obj) throws Exception {
		CloseableHttpResponse response = null;
		@SuppressWarnings("resource")
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		if(obj!=null)
			get.setParams(getParam(obj));
		response = client.execute(get);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return EntityUtils.toString(response.getEntity());
		}
		throw new RuntimeException("http request result code:"
				+ response.getStatusLine().getStatusCode());
	}

	private static BasicHttpParams getParam(Object obj) {
		Field[] fields = obj.getClass().getFields();
		BasicHttpParams httpParam = new BasicHttpParams();
		for (Field f : fields) {
			Object result = ReflectionUtils.getField(f, obj);
			if (result != null)
				httpParam.setParameter(f.getName(), result);
		}
		return httpParam;
	}

	private static List<NameValuePair> postParam(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Field f : fields) {
			ReflectionUtils.makeAccessible(f);
			Object result = ReflectionUtils.getField(f, obj);
			if (result != null)
				formparams.add(new BasicNameValuePair(f.getName(), String
						.valueOf(result)));
		}
		return formparams;
	}
	public static String sendGet(String url,Map<String, String> params) throws Exception {
		if (params != null && !params.isEmpty()) {
			StringBuffer stringBuffer = new StringBuffer();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				try {
					stringBuffer
							.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
							.append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			return doGet(url+"?"+stringBuffer.toString(),null);
		}
		return doGet(url,null);
	}
	/**
	 * 获取升序 排序后的key和value字符串
	 * @param map
	 * @return
	 */
	public static String getURLString(Map<String, String> map) {
		List<String> list = new ArrayList<String>();
		StringBuffer str = new StringBuffer();
		if(null!=map&&!map.isEmpty()){
			for (String key : map.keySet()) {
				list.add(key);
			}
			Collections.sort(list);
			for (String string : list) {
				str.append(string).append("=").append(map.get(string)).append("&");
			}
			str.deleteCharAt(str.length()-1);
		}
		return str.toString();
	}
}
