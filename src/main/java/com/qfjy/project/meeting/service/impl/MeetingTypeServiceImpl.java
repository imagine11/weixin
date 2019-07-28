package com.qfjy.project.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.MeetingType;
import com.qfjy.project.meeting.mapper.MeetingTypeMapper;
import com.qfjy.project.meeting.service.MeetingTypeService;
@Service
public class MeetingTypeServiceImpl implements MeetingTypeService {
	@Autowired
	private MeetingTypeMapper meetingTypeMapper;
	@Override
	public List<MeetingType> selectListByStatusAndSortNum() {
		// TODO Auto-generated method stub
		return meetingTypeMapper.selectListByStatusAndSortNum();
	}

	
}
