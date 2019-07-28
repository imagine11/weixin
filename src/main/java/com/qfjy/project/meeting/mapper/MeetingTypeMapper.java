package com.qfjy.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.qfjy.project.meeting.bean.MeetingType;

public interface MeetingTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingType record);

    int insertSelective(MeetingType record);

    MeetingType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingType record);

    int updateByPrimaryKey(MeetingType record);
    
    //查询状态为1 且按照排序规则进行显示列表类别
    @Select("select * from meetingtype where status=1 order by sortnum asc")
    List<MeetingType> selectListByStatusAndSortNum();
}