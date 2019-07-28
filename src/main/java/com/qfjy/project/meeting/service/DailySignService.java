package com.qfjy.project.meeting.service;

import org.apache.ibatis.annotations.Select;

import com.qfjy.project.meeting.bean.DailySign;

public interface DailySignService {

	 int insertSelective(DailySign record);
	 
	 /**
	     * 根据用户id查询
	     * @param uid
	     * @return
	     */
	   
	 DailySign selectByUid(Integer uid);
	 DailySign selectByPrimaryKey(Integer id);
	 int updateByPrimaryKeySelective(DailySign record);
	 /**
	     * 查询总数
	     */
	   
	    int selectCount();
	    /**
	     * 查询总分比我低的人
	     */
	    
	    int selectCountByPoint(Integer point);
	
}
