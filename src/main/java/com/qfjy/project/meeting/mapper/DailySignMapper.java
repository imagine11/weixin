package com.qfjy.project.meeting.mapper;

import org.apache.ibatis.annotations.Select;

import com.qfjy.project.meeting.bean.DailySign;

public interface DailySignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailySign record);

    int insertSelective(DailySign record);

    DailySign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DailySign record);

    int updateByPrimaryKey(DailySign record);
    
    /**
     * 根据用户id查询
     * @param uid
     * @return
     */
    @Select("select * from dailysign where uid=#{uid}")
    DailySign selectByUid(Integer uid);
    /**
     * 查询总数
     */
    @Select("select count(*) from dailysign")
    int selectCount();
    /**
     * 查询总分比我低的人
     */
    @Select("select count(*) from dailysign where point<=#{point};")
    int selectCountByPoint(Integer point);
}