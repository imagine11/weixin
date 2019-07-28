package com.qfjy.project.meeting.web.controller.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

@RequestMapping("weixin")
@Controller
public class WeiXinOauth {
	@RequestMapping("oauth")  // weixin/oauth
	public void oauth(HttpServletResponse response) throws IOException{
		String url=MenuManager.REAL_URL+"weixin/invoke"; 
		try {
			url=URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/**
		 * 1.是否同意授权
		 */
		String path="https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+MenuManager.appId
				+ "&redirect_uri="+url
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"//显示授权(弹出授权页面)
				+ "&state=java1901"
				+ "#wechat_redirect";
		response.sendRedirect(path);
	}
	@RequestMapping("invoke")  // weixin/invoke
	public String weixinInvoke(HttpServletRequest request){
		// 2 获取code    redirect_uri/?code=CODE&state=STATE。
		String code=request.getParameter("code");
		System.out.println("得到的code"+code);
		// 通过code换取网页授权access_token
		String path="https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+MenuManager.appId
				+ "&secret="+MenuManager.appSecret
				+ "&code="+code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject=WeixinUtil.httpRequest(path, "GET", null);
		System.out.println(jsonObject.toString());
		
		return "/pages/weixin/oauth.jsp";
		
	}
}
