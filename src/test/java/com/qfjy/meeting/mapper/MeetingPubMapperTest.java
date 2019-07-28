package com.qfjy.meeting.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.service.MeetingPubService;

public class MeetingPubMapperTest {
	
	public static void main(String[] args) {
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("spring_core.xml");
		
		MeetingPubService meetingPubService =  context.getBean(MeetingPubService.class);
		 
		MeetingPub m = meetingPubService.selectUserByPrimaryKey("44f336ce-8937-4273-ba0a-2edc8d76f4a9");
		
		User u = m.getUser();
		System.out.println(u.toString());
	}
}
