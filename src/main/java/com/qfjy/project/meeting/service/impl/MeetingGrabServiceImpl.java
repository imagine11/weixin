package com.qfjy.project.meeting.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qfjy.project.meeting.bean.MeetingGrab;
import com.qfjy.project.meeting.exception.MeetingGrabException;
import com.qfjy.project.meeting.mapper.MeetingGrabMapper;
import com.qfjy.project.meeting.service.MeetingGrabService;
@Service
public class MeetingGrabServiceImpl implements MeetingGrabService {
	private static final Logger log = LoggerFactory.getLogger(MeetingGrabServiceImpl.class );
	@Autowired
	MeetingGrabMapper meetingGrabMapper;
	@Override
	public int insertSelective(MeetingGrab record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedate(new Date());
		record.setGrabstatus((short)0);
		
		return meetingGrabMapper.insertSelective(record);
	}
	@Override
	public List<MeetingGrab> selectUserByPid(String pid) {
		// TODO Auto-generated method stub
		return meetingGrabMapper.selectUserByPid(pid);
	}
	@Override
	@Transactional //事务回滚 ,抛出运行时异常 进行
	public int chooseMeetingGrabBy(MeetingGrab grab) {
		int num = meetingGrabMapper.updateMeetingGrabGrabStatusFail(grab);
		if(num<1) {
			//操作所有的抢单者状态为匹配失败时异常
			log.info("操作所有的抢单者状态为匹配失败时异常");
			throw new MeetingGrabException("操作所有的抢单者状态为匹配失败时异常");
			
		}
		int num1 = meetingGrabMapper.updateMeetingGrabGrabStatusSucc(grab);
		if(num1<1) {
			//操作所有抢单者状态为匹配成功时 异常
			log.info("操作所有的抢单者状态为匹配成功时异常");
			throw new MeetingGrabException("操作所有抢单者状态为匹配成功时 异常");
		}
		return 1;
	}

}
