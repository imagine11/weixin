package com.qfjy.project.meeting.mapper;

import org.apache.ibatis.annotations.Select;

import com.qfjy.project.meeting.bean.Weiuser;

public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);
    @Select("select * from weiuser where openid=#{openid}")
    Weiuser selectByOpenid(String openid);
    
}