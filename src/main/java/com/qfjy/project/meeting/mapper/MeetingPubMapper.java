package com.qfjy.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qfjy.project.meeting.bean.MeetingGrab;
import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.bean.User;

public interface MeetingPubMapper {
    int deleteByPrimaryKey(String id);

    int insert(MeetingPub record);

    int insertSelective(MeetingPub record);

    MeetingPub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingPub record);

    int updateByPrimaryKey(MeetingPub record);
    
    //根据日期查询发单表中的最大会议编号
    @Select("select max(pcode) from meetingpub where left(pcode,8)=#{time}")
    String selectMaxPcodeByTime(String time);
    /**
     * 我的会议
     * @param id
     * @return
     */
    @Select("select * from meetingpub where status=1 and uid=#{uid}")
    List<MeetingPub> selectByUid(Integer uid);
    
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
    List<MeetingPub> selectByMeetingPub(@Param("p")MeetingPub meetingpub,@Param("u")User user,@Param("g")MeetingGrab meetingGrab);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatchByIds(String[] ids);
    @Update("update meetingpub set status=#{arg0} where id=#{arg1}")
    int updateStatusById(Integer status,String id);
    
    /**
     * 昨日日报统计
     */
    //昨日发单数量
    @Select("SELECT count(*) FROM meetingPub WHERE DATEDIFF(NOW(), createdate) = 1")
    int selectYestDayMeetingPubCount();
    //今日可抢单数量
    @Select("select tname,count(*) as uid from meetingpub where "
    		+ "id not in (select pid from meetinggrab where grabstatus=1) "
    		+ "group by tname order by uid desc;")
    List<MeetingPub> selectTodayMeetingGrabCount();
    //昨天成功匹配
    @Select("select count(*) from meetinggrab where grabstatus=1 and DATEDIFF(NOW(),grabdate)=0;")
    int selectYestDayMatchCount();
    //昨日成功执行
    @Select("select count(*) from meetingpub where id in (select pid from meetinggrab where grabstatus=1)and DATEDIFF(NOW(),ptime)=1")
    int selectYestDayExecCount();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}