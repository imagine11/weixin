package com.qfjy.project.meeting.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qfjy.project.meeting.bean.MeetingGrab;
import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.commons.util.DateUtil;
import com.qfjy.project.meeting.mapper.MeetingPubMapper;
import com.qfjy.project.meeting.service.MeetingPubService;
@Service
public class MeetingPubServiceImpl implements MeetingPubService {
	@Autowired
	private MeetingPubMapper meetingPubMapper;
	@Override
	public int insertSelective(MeetingPub pub) {
		pub.setId(UUID.randomUUID().toString());
		
		pub.setPcode(this.getPcode(pub.getPtime()));
		pub.setCreatedate(new Date());
		pub.setStatus((short)1); //默认为有效
		
		return meetingPubMapper.insertSelective(pub);
	}
	/**
	 * 会议编号生成规则 代码实现:
	 * 	1 先获取当前时间(召开时间)
	 * 
	 * 	2 截取到年月日 20190628
	 * 
	 * 	3根据20190628进行max查询
	 * 
	 * 如果没有 +001
	 * 如果有+1

	 * @param ptime
	 * @return
	 */
	
	public String getPcode(String ptime) {
		//将2019-06-28 14:20 转换为 20190628
		String time = 	ptime.substring(0, 10).replace("-", "");
		//到数据库中查询20190628格式的编号的最大值
		String result = meetingPubMapper.selectMaxPcodeByTime(time);
		String pcode=null;
		//如果是空就把当前转换过的code加上001
		if(StringUtils.isEmpty(result)) {
			pcode=time+"001";
			//如果不是就在最大值上加1
		}else {
		Long l = Long.parseLong(result)+1;
		pcode=l.toString();
		}
		return pcode;
	}
	@Override
	public List<MeetingPub> selectByUid(Integer uid) {
		// TODO Auto-generated method stub
		return meetingPubMapper.selectByUid(uid);
	}
	@Override
	public MeetingPub selectUserByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return meetingPubMapper.selectUserByPrimaryKey(id);
	}
	@Override
	public List<MeetingPub> selectByMeetingGrabUid(Integer uid, String tname) {
		// TODO Auto-generated method stub
		return meetingPubMapper.selectByMeetingGrabUid(uid, tname);
	}
	@Override
	public List<MeetingPub> selectMeetingGrabByUid(Integer uid) {
		// TODO Auto-generated method stub
		return meetingPubMapper.selectMeetingGrabByUid(uid);
	}
	@Override
	public List<MeetingPub> selectByMeetingPub(MeetingPub meetingpub, User user,MeetingGrab meetingGrab) {
		// TODO Auto-generated method stub
		return meetingPubMapper.selectByMeetingPub(meetingpub, user,meetingGrab);
	}
	@Override
	public int deleteBatchByIds(String[] ids) {
		// TODO Auto-generated method stub
		return meetingPubMapper.deleteBatchByIds(ids);
	}
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return meetingPubMapper.deleteByPrimaryKey(id);
	}
	@Override
	public MeetingPub selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return meetingPubMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(MeetingPub record) {
		// TODO Auto-generated method stub
		return meetingPubMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateStatusById(Integer status, String id) {
		// TODO Auto-generated method stub
		return meetingPubMapper.updateStatusById(status, id);
	}
	/**
	 * 每日日报
	 */
	@Override
	public String logInfo() {
		 StringBuffer str=new StringBuffer();
		
		 str.append("每日日报(今日"+DateUtil.getTodayDate()+"日)\n");

	     str.append("1、昨天("+DateUtil.getYestdayDate()+")发单数量"

	      +meetingPubMapper.selectYestDayMeetingPubCount()+"场\n");

	      str.append(getMeetingGrabCount());

	      str.append( "昨天成功区配" +meetingPubMapper.selectYestDayMatchCount()+"場\n");
	      str.append( "昨天成功抗行" +meetingPubMapper.selectYestDayExecCount()+"場\n");
	      return str.toString();
	
	}
	public String getMeetingGrabCount() {
		//获取当天每个课题有多少场,和一个能抢多少场
		List<MeetingPub> list = meetingPubMapper.selectTodayMeetingGrabCount();
		int sunCount=0;
		StringBuffer sb = new StringBuffer();
		for (MeetingPub m : list) {
			sunCount+=m.getUid();
			sb.append(m.getTname()+"可抢单"+m.getUid()+"\n");
		}
		StringBuffer sb1 = new StringBuffer();
		sb.append("2 今日可抢单"+sunCount+"场\n");
		sb.append(sb1);
		return sb.toString();
		
	}

	
	
	
	
	
}
