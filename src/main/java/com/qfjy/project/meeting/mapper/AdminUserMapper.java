package com.qfjy.project.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qfjy.project.meeting.bean.AdminUser;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
    /**
     * 登录验证
     * @param uname
     * @param upass
     * @return
     */
    @Select("select * from adminuser where status=1 and "
    		+ "uname=#{uname} and upass = #{upass}")
    AdminUser login(@Param("uname")String uname,@Param("upass")String upass);
}