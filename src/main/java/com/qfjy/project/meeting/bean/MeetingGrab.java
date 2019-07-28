package com.qfjy.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class MeetingGrab {
	/*
	 * 主键id
	 */
    private String id;
    /*
     * 备注
     */
    private String remark;
    /*
     * 关联发单表 发单id
     */
    private String pid;
    /*
     * 关联用户表 抢单人
     */
    private Integer uid;
    /*
     * 抢单状态 0未匹配 1已匹配 2匹配失败
     */
    private Short grabstatus;
    /*
     * 抢单时间 (当前时间)
     */
    private Date createdate;
    /*
     * 匹配时间 抢单状态1时,匹配成功时间 2时匹配失败时间
     */
    private Date grabdate;
    //一对一
    private User user;

}