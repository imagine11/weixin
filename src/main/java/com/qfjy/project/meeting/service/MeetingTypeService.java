package com.qfjy.project.meeting.service;

import java.util.List;


import com.qfjy.project.meeting.bean.MeetingType;

public interface MeetingTypeService {

    /**
     * 查询有效的课题类别 并根据排序字段排序
     * @return
     */
   
    List<MeetingType> selectListByStatusAndSortNum();
}
