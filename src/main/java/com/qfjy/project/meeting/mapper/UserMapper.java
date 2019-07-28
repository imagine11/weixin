package com.qfjy.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qfjy.project.meeting.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> listByUser(User user);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatchByIds(Integer[] ids);
   
    @Update("update user set status=#{arg0} where id=#{arg1}")
    int updateStatusById(Integer status,Integer id);
    
    /**
     * 根据wid查询数据
     */
    @Select("select * from user where wid=#{wid}")
    User selectByWid(Integer wid);
    /**
     * 根据邮箱查询
     */
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);
    /**
     * 根据邮箱添加wid
     */
    @Update("update user set wid=#{param1} where email=#{param2}")
    int updateWidByEmail(Integer wid,String email);
    
    /**
     * 判断当前微信用户 openid是否进行了绑定
     */
    @Select("select * from user where wid= "
    		+ "(select id from weiuser where openid=#{openid}")
    User selectByOpenid(String openid);
}
    
    
    
