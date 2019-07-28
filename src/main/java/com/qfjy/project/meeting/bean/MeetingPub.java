package com.qfjy.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class MeetingPub {
    private String id;
    /*会议编号*/
    private String pcode;
    /*召开时间*/
    private String ptime;
    /*课题类别*/
    private String tname;
    /*主题*/
    private String ptitle;
    //地区
    private String zone;
    //备注
    private String remark;
    //发单时间
    private Date createdate;
    //状态
    private Short status;
    
    private Integer uid;
    
    //多对一
    private User user;
    
    //一对一
    private MeetingGrab meetingGrab;
}