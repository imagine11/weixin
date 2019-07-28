package com.qfjy.project.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.DailySign;
import com.qfjy.project.meeting.mapper.DailySignMapper;
import com.qfjy.project.meeting.service.DailySignService;
@Service
public class DailySignServiceImpl implements DailySignService {
	@Autowired
	private DailySignMapper dailySignMapper;
	@Override
	public int insertSelective(DailySign record) {
		// TODO Auto-generated method stub
		return dailySignMapper.insertSelective(record);
	}
	@Override
	public DailySign selectByUid(Integer uid) {
		// TODO Auto-generated method stub
		return dailySignMapper.selectByUid(uid);
	}
	@Override
	public DailySign selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dailySignMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(DailySign record) {
		// TODO Auto-generated method stub
		return dailySignMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return dailySignMapper.selectCount();
	}
	@Override
	public int selectCountByPoint(Integer point) {
		// TODO Auto-generated method stub
		return dailySignMapper.selectCountByPoint(point);
	}

}
