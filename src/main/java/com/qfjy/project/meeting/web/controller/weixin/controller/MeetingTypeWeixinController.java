package com.qfjy.project.meeting.web.controller.weixin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfjy.project.meeting.bean.MeetingType;
import com.qfjy.project.meeting.service.MeetingTypeService;

@Controller
@RequestMapping("MeetingTypeWeixin")
public class MeetingTypeWeixinController {

	@Autowired
	private MeetingTypeService meetingTypeService;
	@RequestMapping("list")
	@ResponseBody
	public List<MeetingType> selectList(){   //MeetingTypeWeixin/list
		
		return meetingTypeService.selectListByStatusAndSortNum();
	}
}
