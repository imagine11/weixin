package com.qfjy.project.meeting.bean;

import lombok.Data;

@Data
public class Weiuser {
    private Integer id;

    private String openid;

    private String nickname;

    private Short sex;

    private String city;

    private String country;

    private String province;

    private String headimgurl;

    private Short subscribe;

    private String language;

    private String remark;

    //补全JSON转对象的要求属性
    private String subscribe_time;
    private String groupid;
    private String[]  tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
    
    private String[] privilege;
    private String unionid;
}