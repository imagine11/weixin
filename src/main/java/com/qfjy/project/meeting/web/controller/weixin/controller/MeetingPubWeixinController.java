package com.qfjy.project.meeting.web.controller.weixin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.service.MeetingPubService;

@Controller
@RequestMapping("meetingPubWeixin")
public class MeetingPubWeixinController {
	private static final Logger log = LoggerFactory.getLogger(MeetingPubWeixinController.class);
	@Autowired
	private MeetingPubService meetingPubService;
	@RequestMapping("add")   //meetingPubWeixin/add
	@ResponseBody
	public int addMeetingPub(MeetingPub pub) {
		
		int num = meetingPubService.insertSelective(pub);
		log.info("会议发布方法"+pub+num);
		return num;
	}
	
	/**
	 *我的会议
	 */
	@RequestMapping("uid/{uid}")//meetingPubWeixin/uid
	@ResponseBody
	public List<MeetingPub> selectByUid(@PathVariable("uid")Integer uid){
		
		
		return meetingPubService.selectByUid(uid);
	}
	@RequestMapping("info") //meetingPubWeixin/info
	public ModelAndView meetingPubInfo(@RequestParam("id") String id) {
		
		ModelAndView model = new ModelAndView();
		MeetingPub m = meetingPubService.selectUserByPrimaryKey(id);
		model.addObject("m", m);
		model.setViewName("/pages/weixin/meetingPub/meetingPubInfo.jsp");
		return model;
	}
	
	
}
