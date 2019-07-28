package com.qfjy.project.weixin.api.accessToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
/**
 * accessToken 一天只能调用2000次  一次生效两个小时 当频繁的获取时会浪费很多 因为一次生效两个小时
 * 所以我们要控制每个accessToken的时间
 * 
 * 作用： access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
 * 
 * 条件： access_token的存储至少要保留512个字符空间。 access_token的有效期目前为2个小时，需定时刷新，
 * 重复获取将导致上次获取的access_token失效。 access_token接口（API）每日调用上限不超过2000次,
 *
 * @author 我是猫
 *
 */
public class AccessTokenThread extends Thread{
	//日志
	public static final Logger LOG = LoggerFactory.getLogger(AccessTokenThread.class);
	public static String ACCESS_TOKEN_VAL;
	@Override
	//每两小时获取一次
    public void run() {
       while(true) {
    	   ACCESS_TOKEN_VAL = getAccessToken();
    	   LOG.info("得到：access_token_val:"+ACCESS_TOKEN_VAL);
    	   try {
			Thread.sleep(3000*1000);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
			
		}
       }
		
    }
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public static String getAccessToken() {
		
		String url = ACCESS_TOKEN_URL.replaceAll("APPID", MenuManager.appId).replace("APPSECRET", MenuManager.appSecret);
		//获取accessToken
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
		//判断是否获取成功
		if(jsonObject.get("access_token")!=null) {
			LOG.info("access_token获取成功");
			return jsonObject.getString("access_token");
		}else {
			LOG.error("获取access_token"+jsonObject.toString());
		}
		return null;
	}
}
