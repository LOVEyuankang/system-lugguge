package com.net.metadata.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SpringUtil {

	private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

	private static String springUtil(String keyname){
		Properties props = new Properties();
		String result="";
	    while(true){  
	        try {  
	            props = PropertiesLoaderUtils.loadAllProperties("application.properties");
	            for(Object key:props.keySet()){
	            	logger.info(key + " : " + props.get(key));
	                if(key.equals(keyname)){
	                	result = props.get(key).toString();
	                	break;
	                }
	            }  
	        } catch (IOException e) {
				logger.error("文件读取失败，错误原因：{}", e.getMessage());
	        }
		    return result;
	        //try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}  
	    }  
	}

	public static String sendMessage(String msg){
		String url = springUtil("bids_url");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("message", msg));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
			httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
			HttpResponse response = HttpClients.createDefault().execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
	    		/*读返回数据*/
				@SuppressWarnings("unused")
				String conResult = EntityUtils.toString(response.getEntity());
				logger.info("读返回数据: {}", conResult);
			}
		}catch(Exception e){
			logger.error("消息发送失败，错误原因：{}", e.getMessage());
		}
		return "";
	}

	public static void main(String[] args) {
		System.out.println(springUtil("printSearch_url"));
		System.out.println(springUtil("bids_url"));
	}
}
