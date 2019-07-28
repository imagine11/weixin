package com.qfjy.project.meeting.service;

import java.util.List;

import com.qfjy.project.meeting.bean.Role;

public interface RoleService {
	/**
	 * 查询所有
	 * @return
	 */
	 List<Role> listByRole(Role role);
	 
	 int deleteByPrimaryKey(Integer id);
	 
	    /**
	     * 批量删除
	     * @param ids
	     * @return
	     */
	    int deleteBatchByIds(Integer[] ids);
	    
	    Role selectByPrimaryKey(Integer id);
	    
	    int updateByPrimaryKeySelective(Role record);
	    
	    int updateStatusById(Integer status,Integer id);
	    
	    int insertSelective(Role record);

}
