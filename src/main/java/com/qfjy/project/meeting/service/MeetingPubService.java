package com.qfjy.project.meeting.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qfjy.project.meeting.bean.MeetingGrab;
import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.bean.User;

public interface MeetingPubService {

	/**
	 * 会议发布 添加
	 */
	 int insertSelective(MeetingPub record);
	 
	 /**
	     * 我的会议
	     * @param id
	     * @return
	     */
	  
	    List<MeetingPub> selectByUid(Integer id);
	    /**
	     * 多对一  查询 关联用户信息
	     * @param id
	     * @return
	     */
	    MeetingPub selectUserByPrimaryKey(String id);
	    
	    /**
	     * 会议抢单 可抢单列表
	     */
	    List<MeetingPub> selectByMeetingGrabUid(Integer uid,String tname);
	    
	    /**
	     * 我的抢单列表功能
	     * 根据抢单用户uid 查询发单信息,抢单信息
	     */
	    List<MeetingPub> selectMeetingGrabByUid(Integer uid);
	    
	    /**
	     * 后台管理显示发单列表
	     * 可根据发单人姓名查询
	     */
	    List<MeetingPub> selectByMeetingPub(MeetingPub meetingpub,User user,MeetingGrab meetingGrab);

	    /**
	     * 批量删除
	     * @param ids
	     * @return
	     */
	    int deleteBatchByIds(String[] ids);
	    int deleteByPrimaryKey(String id);
	    MeetingPub selectByPrimaryKey(String id);
	    int updateByPrimaryKeySelective(MeetingPub record);
	    int updateStatusById(Integer status,String id);

		 /**
		  * 
		  * 每日日报
		  */
	    String logInfo();
}
