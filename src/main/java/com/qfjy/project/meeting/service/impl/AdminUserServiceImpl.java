package com.qfjy.project.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.AdminUser;
import com.qfjy.project.meeting.mapper.AdminUserMapper;
import com.qfjy.project.meeting.service.AdminUserService;
@Service
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@Override
	public AdminUser login(String uname, String upass) {
		
		return adminUserMapper.login(uname, upass);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminUser record) {
		// TODO Auto-generated method stub
		return adminUserMapper.updateByPrimaryKeySelective(record);
	}

	

}
