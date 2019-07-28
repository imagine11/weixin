package com.qfjy.project.meeting.web.controller.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.bean.Weiuser;
import com.qfjy.project.meeting.service.UserService;
import com.qfjy.project.meeting.service.WeiuserService;
import com.qfjy.project.weixin.api.userInfo.UserInfoService;
import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

@RequestMapping("wx")
@Controller
public class WeixinManagerOauth {
	@Autowired
	private WeiuserService weiuserService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("oauth")
	public void oauth(HttpServletResponse response) throws IOException {
		String url = MenuManager.REAL_URL+"wx/oauth/invoke";
		
		try {
			URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 1.是否同意授权
		 */
		String path = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+MenuManager.appId
				+ "&redirect_uri="+url
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo" //弹出页面请求授权
				+ "&state=java1901"
				+ "#wechat_redirect ";
		response.sendRedirect(path);
	}
	@RequestMapping("oauth/invoke")  // weixin/invoke
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
		String access_token=jsonObject.getString("access_token");
		String openid = jsonObject.getString("openid");
		
		/**
		 * 判断用户是否绑定功能
		 * 1 通过openid获得weiuser主键id
		 */
		Weiuser weiuser = weiuserService.selectByOpenid(openid);
		if(weiuser==null) {
			//获取用户信息
			String url = "https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+access_token
					+ "&openid="+openid
					+ "&lang=zh_CN";
			JSONObject jsonUserInfo = WeixinUtil.httpRequest(url, "GET", null);
			//2 json转为javabean对象
			weiuser = userInfoService.convertJSONWeiuser(jsonUserInfo);
			//3 进行插入功能 
			int num = userInfoService.addWeruser(weiuser);
		}
			//判断是否进行绑定
			User u = userService.selectByWid(weiuser.getId());
			
			if(u==null) { //未进行绑定 跳转到登录页面
				return "/pages/weixin/user/login.jsp?wid="+weiuser.getId();
			}else {
				request.setAttribute("user", u);
				return "/pages/weixin/user/userInfo.jsp";
			}
		
		
		
		
	}
	@RequestMapping("oauth/meetingPub")
	public void oauthMeetingPub(HttpServletResponse response) throws IOException {
		String url = MenuManager.REAL_URL+"wx/oauth/meetingPubInvoke";
		
		try {
			URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 1.是否同意授权
		 */
		String path = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+MenuManager.appId
				+ "&redirect_uri="+url
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo" //弹出页面请求授权
				+ "&state=java1901"
				+ "#wechat_redirect ";
		response.sendRedirect(path);
	}
	
	@RequestMapping("oauth/meetingPubInvoke")  // weixin/invoke
	public String meetingPubInvoke(HttpServletRequest request){
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
		String access_token=jsonObject.getString("access_token");
		String openid = jsonObject.getString("openid");
		
		/**
		 * 判断用户是否绑定功能
		 * 1 通过openid获得weiuser主键id
		 */
		Weiuser weiuser = weiuserService.selectByOpenid(openid);
		if(weiuser==null) {
			//获取用户信息
			String url = "https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+access_token
					+ "&openid="+openid
					+ "&lang=zh_CN";
			JSONObject jsonUserInfo = WeixinUtil.httpRequest(url, "GET", null);
			//2 json转为javabean对象
			weiuser = userInfoService.convertJSONWeiuser(jsonUserInfo);
			//3 进行插入功能 
			int num = userInfoService.addWeruser(weiuser);
		}
			//判断是否进行绑定
			User u = userService.selectByWid(weiuser.getId());
			
			if(u==null) { //未进行绑定 跳转到登录页面
				return "/pages/weixin/user/login.jsp?wid="+weiuser.getId();
			}else {
				//绑定后 判断用户是发单组装还是抢单组
				if(u.getRid()==1) { //发单组  跳到发单页面
					request.setAttribute("user", u);
					return "/pages/weixin/meetingPub/meetingPub.jsp";
				}else {//抢单组  跳转无权限访问页面
					
					return "/pages/weixin/unauth.jsp";
				}
				
			}
		
		
		
		
	}
}
