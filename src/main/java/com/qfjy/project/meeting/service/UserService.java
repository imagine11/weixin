package com.qfjy.project.meeting.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qfjy.project.meeting.bean.User;

public interface UserService {
	/**
	 * 查询所有
	 * @return
	 */
	 List<User> listByUser(User user);
	 
	 int deleteByPrimaryKey(Integer id);
	 
	    /**
	     * 批量删除
	     * @param ids
	     * @return
	     */
	    int deleteBatchByIds(Integer[] ids);
	    
	    User selectByPrimaryKey(Integer id);
	    
	    int updateByPrimaryKeySelective(User record);
	    
	    int updateStatusById(Integer status,Integer id);
	    
	    int insertSelective(User user);
	    
	    /**
	     * 根据wid查询数据
	     */
	    
	    User selectByWid(Integer wid);
	    /**
	     * 根据邮箱查询
	     */
	   
	    User selectByEmail(String email);
	    
	    /**
	     * 根据邮箱添加wid
	     */
	  
	    int updateWidByEmail(Integer wid,String email);
	    
	    /**
	     * 判断当前微信用户 openid是否进行了绑定
	     */
	   
	    User selectByOpenid(String openid);
}
