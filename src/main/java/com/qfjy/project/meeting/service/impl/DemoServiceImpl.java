package com.qfjy.project.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.Demo;
import com.qfjy.project.meeting.mapper.DemoMapper;
import com.qfjy.project.meeting.service.DemoService;

@Service
public class DemoServiceImpl  implements DemoService{

	@Autowired
	private DemoMapper demoMapper;
	
	@Override
	public Demo selectByPrimaryKey(Integer id) {
		
		return demoMapper.selectByPrimaryKey(id);
	}

}
