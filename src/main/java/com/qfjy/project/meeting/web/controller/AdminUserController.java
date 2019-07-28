package com.qfjy.project.meeting.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qfjy.project.meeting.bean.AdminUser;
import com.qfjy.project.meeting.service.AdminUserService;
import com.qfjy.project.meeting.web.util.ValidateColorServlet;

@Controller
@RequestMapping("adminUser")
public class AdminUserController {
	/**
	 * 验证前台登录功能
	 * @param uname
	 * @param upass
	 * @param vcode
	 * @param request
	 * @param session
	 * @return
	 * 验证码要先进行验证,验证成功才能执行登录操作
	 */
	@Autowired
	private AdminUserService adminUserService;
	@RequestMapping("login")
	public String login(@RequestParam("uname") final String uname,
						@RequestParam("upass") final String upass,
						@RequestParam("vcode") final String vcode,
						HttpServletRequest request,
						HttpSession session
						) throws IllegalAccessException, InvocationTargetException {
		/**
		 * 验证码匹配
		 * tips=1:验证码错误
		 * tips=4:用户名或密码错误
		 */
		//1 将生成的验证码在session中取出
		String codeKey = (String) session.getAttribute(ValidateColorServlet.CHECK_CODE_KEY);
		//2比较两者验证码是否相同
		if(!codeKey.equalsIgnoreCase(vcode)) {
			//如果验证码不想等
			
			return "redirect:/pages/manager/login.jsp?uname="+uname+"&tips=1";
		}
		//执行登录功能
		AdminUser adminUser = adminUserService.login(uname, upass);
		if(adminUser==null){
			//登录失败
			return "redirect:/pages/manager/login.jsp?uname="+uname+"&tips=4";
		}else {//登录成功
			/**
			 * 判断是否是首次登录(判断count是否为空或0)
			 * 	1如果是首次登录
			 * 更新登录表 ip地址为当前ip 上次时间:当前登录时间,登录次数:1 同时要把数据在页面端显示
			 *  2如果不是首次
			 *  页面显示:上次的ip,上次的时间,登录次数+1
			 *  更新登录表 ip数据:本次,时间改为当前时间.登录次数:上次的+1
			 */
			String lastip = request.getRemoteAddr();
			if(StringUtils.isEmpty(adminUser.getLastip())) {
				
				
				adminUser.setCount(1);
				adminUser.setLastdate(new Date());
				adminUser.setLastip(lastip);
				
				adminUserService.updateByPrimaryKeySelective(adminUser);
				session.setAttribute("adminUser", adminUser);
			}else {//非首次登录
				adminUser.setCount(adminUser.getCount()+1);
				//显示上次的时间和时间
				session.setAttribute("adminUser", adminUser);
				//更新本次的ip和时间
				AdminUser a1 = new AdminUser();
				BeanUtils.copyProperties(a1, adminUser);
				
				Date currtDate = new Date();//本次时间
				a1.setLastip(lastip);
				a1.setLastdate(currtDate);
				
				adminUserService.updateByPrimaryKeySelective(a1);
			}
		}
		return"redirect:/pages/manager/main.jsp";
	}
	
}
