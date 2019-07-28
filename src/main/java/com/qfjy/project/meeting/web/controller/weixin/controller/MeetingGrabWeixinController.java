package com.qfjy.project.meeting.web.controller.weixin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfjy.project.meeting.bean.MeetingGrab;
import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.exception.MeetingGrabException;
import com.qfjy.project.meeting.service.MeetingGrabService;
import com.qfjy.project.meeting.service.MeetingPubService;
@RequestMapping("meetingGrabWeixin")
@Controller
public class MeetingGrabWeixinController {
	private static final Logger log = LoggerFactory.getLogger(MeetingGrabWeixinController.class);
	@Autowired
	private MeetingPubService meetingPubService;
	@Autowired
	private MeetingGrabService meetingGrabService;
	/**
	 * 根据抢单用户id 查询可抢单列表信息
	 * @param uid
	 * @param tname
	 * @return
	 */
	@RequestMapping("list")   //meetingGrabWeixin/list
	@ResponseBody
	public List<MeetingPub> selectByMeetingGrabUid(
					@RequestParam("uid") final Integer uid,
					@RequestParam("tname")final String tname){
		
		
		
		return meetingPubService.selectByMeetingGrabUid(uid, tname);
	}
	/**
	 * 我的抢单列表信息
	 * @param uid
	 * @param pid
	 * @param map
	 * @return
	 */
	@RequestMapping("{uid}")
	@ResponseBody
	public List<MeetingPub> selectMeetingGrabByUid(@PathVariable("uid")Integer uid){
		List<MeetingPub> list= meetingPubService.selectMeetingGrabByUid(uid);
		return list;
	}
	
	
	@RequestMapping("grabTo")//meetingGrabWeixin/grabTo
	public String grabTo(@RequestParam("uid")final Integer uid,
						 @RequestParam("pid")final String pid,
						 Map<String,Object> map) {
		map.put("uid", uid);
		map.put("pid",pid);
		
		return "/pages/weixin/meetingGrab/meetingGrabAdd.jsp";
	}
	
	/**
	 * 进行抢单功能
	 * @param grab
	 * @return
	 */
	@RequestMapping(value="add" ,method=RequestMethod.POST)
	@ResponseBody
	public String grabAdd(MeetingGrab grab) {
		int num = meetingGrabService.insertSelective(grab);
		log.info("会议抢单功能操作完成"+num);
		return "1";
	}
	/**
	 * 选择讲者页面 通过pid查询
	 */
	@RequestMapping("grabList") //meetingGrabWeixin/grabList
	@ResponseBody
	public List<MeetingGrab> selectListByPid(@RequestParam("pid") final String pid){
		List<MeetingGrab>list = meetingGrabService.selectUserByPid(pid);
		return list;
	}
	
	/**
	 *选择讲者--就选你功能
	 */
	@RequestMapping("chooseGrabList") //meetingGrabWeixin/chooseGrabList
	@ResponseBody
	public int chooseGrabList(MeetingGrab grab){
		int num = 0;
		try {
			grab.setGrabdate(new Date());
			num = meetingGrabService.chooseMeetingGrabBy(grab);
		}catch(MeetingGrabException e){
			System.out.println(e.getMessage());
		}
		
		return num;
	}
	
}
