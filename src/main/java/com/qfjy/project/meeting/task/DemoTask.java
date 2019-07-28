package com.qfjy.project.meeting.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.qfjy.project.meeting.service.MeetingPubService;
import com.qfjy.project.weixin.api.messageCustom.MessageCustomApi;
import com.qfjy.project.weixin.api.messageCustom.bean.MessageBean;
import com.qfjy.project.weixin.api.messageCustom.bean.MessageText;

import net.sf.json.JSONObject;

@Service
public class DemoTask {
	@Autowired
	private MessageCustomApi messageCustomApi;
	@Autowired
	private MeetingPubService meetingPubService;
	@Scheduled(cron="0/5 * * * * ? ")
	public void task() {
		MessageBean messageBean = new MessageBean();
		messageBean.setTouser("oW2Jm56-D1VW4Xdod8L49m3MzHQ4");
		messageBean.setMagtype("text");
		MessageText text = new MessageText();
		text.setContent(meetingPubService.logInfo());
		
		JSONObject json = 	JSONObject.fromObject(messageBean);
		messageCustomApi.sendMessage(json);
	}
}
