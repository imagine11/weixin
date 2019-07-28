package com.qfjy.project.weixin.api.userInfo;import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfjy.project.meeting.bean.Weiuser;
import com.qfjy.project.meeting.service.WeiuserService;
import com.qfjy.project.weixin.api.accessToken.AccessTokenThread;
import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * 当用户关注此公众号时,获取用户信息,存入数据库
 * @author 我是猫
 *
 */
@Service
public class UserInfoService {

	/**
	 * 1 获取微信api接口,获取用户信息
	 * 接口调用请求说明
		http请求方式: GET
		https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	 */
	@Autowired
	private WeiuserService weiuserService;
	//日志
	public static final Logger LOG = LoggerFactory.getLogger(UserInfoService.class);
	
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
							
	public void userInfoService(String openId) {
		
		//防止数据库添加重复信息
		Weiuser w = weiuserService.selectByOpenid(openId);
		if(w==null) {	
		//1获得微信用户信息
		JSONObject json = this.getUserInfo(openId);
		//2 json转为javabean对象
		Weiuser weiuser = this.convertJSONWeiuser(json);
		//3 进行插入功能 
		int num = this.addWeruser(weiuser);
		LOG.info("微信用户信息添加成功");
		}else {
			LOG.info("微信用户已存在");
		}
	}
	
	
	
	
	
	//用户的openId
	public JSONObject getUserInfo(String openId) {
		
		String url = USER_INFO_URL.replace("ACCESS_TOKEN", AccessTokenThread.ACCESS_TOKEN_VAL).replace("OPENID", openId);
		
		JSONObject json = WeixinUtil.httpRequest(url,"GET", null);
		System.out.println(json.toString());
		
		return json;
	}
	
	 /**
	  * 2 JSON转对象
	  * @param json
	  * @return
	  */
	public Weiuser convertJSONWeiuser(JSONObject json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Weiuser weiuser=null;
		try {
			 weiuser = objectMapper.readValue(json.toString(),Weiuser.class );
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} 
		return weiuser;
	}
	/**
	 * 3 将对象存入数据库
	 */
	public int addWeruser(Weiuser weiuser) {
		
		return weiuserService.insertSelective(weiuser);
	}
}
