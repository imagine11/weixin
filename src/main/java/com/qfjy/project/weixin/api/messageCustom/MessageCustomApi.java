package com.qfjy.project.weixin.api.messageCustom;

import org.springframework.stereotype.Service;

import com.qfjy.project.weixin.api.accessToken.AccessTokenThread;
import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
@Service
public class MessageCustomApi {

	private static String MESSAGE_CUSTOM_API = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	public void sendMessage(JSONObject json) {
		
		String url = MESSAGE_CUSTOM_API.replace("ACCESS_TOKEN", AccessTokenThread.getAccessToken());
		
		WeixinUtil.httpRequest(url, "post", json.toString());
	
	}
}
