package com.qfjy.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class DailySign {
    private Integer id;
    /*
     * 积分总数
     */
    private Integer point;
    /*
     * 签到时间
     */
    private Date signtime;
    /*
     * 用户id
     */
    private Integer uid;
    /*
     * 签到状态
     */
    private Integer status;
    /*
     * 签到总次数
     */
    private Integer num;
    /*
     * 连续签到次数 断了就从0开始
     */
    private Integer count;
    
    private String signstr;

}