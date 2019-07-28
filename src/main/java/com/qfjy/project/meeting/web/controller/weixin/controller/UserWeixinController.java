package com.qfjy.project.meeting.web.controller.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.service.UserService;

@RequestMapping("userWeixin")
@Controller
public class UserWeixinController {
	@Autowired
	UserService userService;
	/**
	 * 绑定邮箱
	 * 邮箱存在,被人绑定过/邮箱存在,没被人绑定过/邮箱不存在
	 * @param email
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public String login(@RequestParam("email")final String email,@RequestParam("wid")final Integer wid) {
		User u = userService.selectByEmail(email);
		//如果邮箱不存在
		if(u==null) {
			return "1";
		}else {//如果邮箱存在 判断是否被人绑定过
			if(u.getWid()!=null) {
				// 邮箱存在,被人绑定过
				return "2";
			}else {
				//邮箱存在,没人绑定,,进行绑定功能
			int num = userService.updateWidByEmail(wid, email);
				return "3";
			}
		}
		
	}
	/**
	 * 更新个人信息功能
	 */
	@RequestMapping("update")//userWeixin/update
	@ResponseBody
	public String updateUserInfo( User u) {
		int num = userService.updateByPrimaryKeySelective(u);
		return num+"";
	}
	
}
