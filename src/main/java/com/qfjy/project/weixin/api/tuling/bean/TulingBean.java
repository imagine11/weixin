package com.qfjy.project.weixin.api.tuling.bean;

import lombok.Data;

@Data
public class TulingBean {

	private Integer reqType=0;
	private Perception perception;
	
	private UserInfo userInfo;
}
