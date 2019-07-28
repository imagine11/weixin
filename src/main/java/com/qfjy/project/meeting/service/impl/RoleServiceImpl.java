package com.qfjy.project.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.bean.Role;
import com.qfjy.project.meeting.mapper.RoleMapper;
import com.qfjy.project.meeting.service.RoleService;
@Service
public class RoleServiceImpl implements  RoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> listByRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.listByRole(role) ;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteBatchByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return roleMapper.deleteBatchByIds(ids);
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateStatusById(Integer status, Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.updateStatusById(status, id);
	}

	@Override
	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(record);
	}

}
