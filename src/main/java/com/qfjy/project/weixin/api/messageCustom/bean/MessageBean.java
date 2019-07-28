package com.qfjy.project.weixin.api.messageCustom.bean;

import lombok.Data;

@Data
public class MessageBean {

	
	private String touser;
	private String magtype;
	
	private MessageText text;
}
