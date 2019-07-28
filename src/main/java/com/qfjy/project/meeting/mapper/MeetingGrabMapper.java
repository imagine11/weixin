package com.qfjy.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.qfjy.project.meeting.bean.MeetingGrab;

public interface MeetingGrabMapper {
    int deleteByPrimaryKey(String id);

    int insert(MeetingGrab record);

    int insertSelective(MeetingGrab record);

    MeetingGrab selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingGrab record);

    int updateByPrimaryKey(MeetingGrab record);
    /**
     * 一对一  通过pid查询抢单信息和用户信息
     * @param pid
     * @return
     */
    List<MeetingGrab> selectUserByPid(String pid);
    //根据发单pid 修改所有的抢单状态未匹配失败状态
    @Update("update meetinggrab set grabstatus=2,grabdate=#{grabdate} where pid=#{pid}")
    int updateMeetingGrabGrabStatusFail(MeetingGrab grab);
    //根据发单pid 修改指定用户的抢单状态为成功状态
    @Update("update meetinggrab set grabstatus=1,grabdate=#{grabdate} where pid=#{pid} and uid=#{uid}")
    int updateMeetingGrabGrabStatusSucc(MeetingGrab grab);
    
}