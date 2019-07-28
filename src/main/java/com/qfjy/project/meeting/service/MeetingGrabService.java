package com.qfjy.project.meeting.service;

import java.util.List;

import com.qfjy.project.meeting.bean.MeetingGrab;

public interface MeetingGrabService {

	
	 int insertSelective(MeetingGrab record);

	 /**
	     * 一对一  通过pid查询抢单信息和用户信息
	     * @param pid
	     * @return
	     */
	    List<MeetingGrab> selectUserByPid(String pid);
	    
	    /**
	     * 就选你功能
	     */
	    int chooseMeetingGrabBy(MeetingGrab rab);
}
