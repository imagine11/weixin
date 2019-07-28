package com.qfjy.project.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.Weiuser;
import com.qfjy.project.meeting.mapper.WeiuserMapper;
import com.qfjy.project.meeting.service.WeiuserService;
@Service
public class WeiuserServiceImpl implements WeiuserService{
	@Autowired
	private WeiuserMapper weiuserMapper;
	@Override
	//添加方法实现类
	public int insertSelective(Weiuser record) {
		
		return weiuserMapper.insertSelective(record);
	}
	@Override
	public Weiuser selectByOpenid(String openid) {
		
		return weiuserMapper.selectByOpenid(openid);
	}

	

}
