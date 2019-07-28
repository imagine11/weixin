package com.qfjy.project.meeting.service;


import com.qfjy.project.meeting.bean.AdminUser;

public interface AdminUserService {

	/**
     * 登录验证
     * @param uname
     * @param upass
     * @return
     */
   
    AdminUser login(String uname,String upass);
    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdminUser record);
}
