package com.qfjy.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class User {
    private Integer id;

    private String name;

    private String email;

    private String telephone;

    private String province;

    private String city;

    private String zone;

    private Integer rid;

    private Short status;

    private Date createdate;

    private Integer wid;


}