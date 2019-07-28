package com.qfjy.project.meeting.web.controller.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qfjy.project.meeting.service.DailySignService;

@Controller
@RequestMapping("dailySign")
public class DailySignWeixinController {
	@Autowired
	private DailySignService dailySignService;
	
	
}
