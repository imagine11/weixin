package com.qfjy.project.weixin.api.Hitokoto;

import org.springframework.stereotype.Service;

import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
@Service
public class HitokotoApi {
	//一言api
	public static final String HITOKOTO_URL_API = "https://v1.hitokoto.cn/";
	
	public String sendMessage() {
		JSONObject jsonObject = WeixinUtil.httpRequest(HITOKOTO_URL_API, "GET", null);
		
		return jsonObject.getString("hitokoto")+"---"+jsonObject.getString("from");
	}
	
	
	public static void main(String[] args) {
		JSONObject jsonObject = WeixinUtil.httpRequest(HITOKOTO_URL_API, "GET", null);
		System.out.println(jsonObject.getString("hitokoto")+"---"+jsonObject.getString("from"));
	}
}
