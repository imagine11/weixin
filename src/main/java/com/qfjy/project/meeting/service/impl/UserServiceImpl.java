package com.qfjy.project.meeting.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.mapper.UserMapper;
import com.qfjy.project.meeting.service.UserService;
@Service
public class UserServiceImpl implements  UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> listByUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.listByUser(user) ;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteBatchByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return userMapper.deleteBatchByIds(ids);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateStatusById(Integer status, Integer id) {
		// TODO Auto-generated method stub
		return userMapper.updateStatusById(status, id);
	}

	@Override
	public int insertSelective(User record) {
		
		record.setCreatedate(new Date());
		
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByWid(Integer wid) {
		// TODO Auto-generated method stub
		return userMapper.selectByWid(wid);
	}

	@Override
	public User selectByEmail(String email) {
		// TODO Auto-generated method stub
		return userMapper.selectByEmail(email);
	}

	@Override
	public int updateWidByEmail(Integer wid, String email) {
		// TODO Auto-generated method stub
		return userMapper.updateWidByEmail(wid, email);
	}

	@Override
	public User selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return userMapper.selectByOpenid(openid);
	}

}
