package com.qfjy.project.meeting.service;

import com.qfjy.project.meeting.bean.Weiuser;

public interface WeiuserService {
	/**
	 * 插入方法
	 * @param record
	 * @return
	 */
	int insertSelective(Weiuser record);
	/**
	 * 根据openid查询对象
	 * @param openid
	 * @return
	 */
	 Weiuser selectByOpenid(String openid);
}
