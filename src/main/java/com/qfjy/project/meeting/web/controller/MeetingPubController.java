package com.qfjy.project.meeting.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qfjy.project.meeting.bean.MeetingGrab;
import com.qfjy.project.meeting.bean.MeetingPub;
import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.service.MeetingPubService;

@Controller
@RequestMapping("meetingPub")
public class MeetingPubController {
	@Autowired
	private MeetingPubService meetingPubService;
	/**
	 * 查询所有发单数据 可以根据发单人姓名查询
	 * @param meetingpub
	 * @param user
	 * @return
	 */
	@RequestMapping("list") //meetingPub/list
	public ModelAndView selectByMeetingPub(MeetingPub meetingpub,User user,MeetingGrab meetingGrab) {
		
		ModelAndView model = new ModelAndView();
		
		List<MeetingPub> list = meetingPubService.selectByMeetingPub(meetingpub, user,meetingGrab);
		model.addObject("listMeetingpub", list);
		model.addObject("meetingGrab", meetingGrab);
		model.addObject("user", user);
		model.addObject("meetingpub", meetingpub);
		model.setViewName("/pages/manager/meetingPub/meetingPub_list.jsp");
		return model;
		
	}
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}",method=RequestMethod.POST)
	@ResponseBody
	public int deleteByPrimaryKey(@PathVariable("id")final String id) {
	
		return meetingPubService.deleteByPrimaryKey(id);
	}
	/**
	 * 根据多个id主键,进行批量删除 
	 */
	@RequestMapping("batch/{ids}")
	@ResponseBody
	public int deleteBatchByIds(@PathVariable("ids")String ids) {
		
		String[] arr = ids.split(",");
		String[] ids1 = new String[arr.length];
		for(int i = 0;i<arr.length;i++) {
			ids1[i]=arr[i];
		}
		return meetingPubService.deleteBatchByIds(ids1);
	}
	
	/**
	 * 修改功能，先根据ID进行查询
	 */
	@RequestMapping("selectById")   // /selectById?id=1
	public ModelAndView  selectById(@RequestParam("id")String id){
		ModelAndView model=new ModelAndView();
		MeetingPub meetingPub=meetingPubService.selectByPrimaryKey(id);
		model.setViewName("/pages/manager/meetingPub/meetingPub_edit.jsp");
		model.addObject("meetingPub", meetingPub);
		return model;
	}
	
	/**
	 * 修改功能，再进行修改
	 */
	@RequestMapping("updateTo")    // role/updateTo
	public ModelAndView updateByRole(MeetingPub meetingPub){
		ModelAndView model=new ModelAndView();
		meetingPubService.updateByPrimaryKeySelective(meetingPub);
		model.setViewName("redirect:/meetingPub/list");
		return model;
		
	}
	/**
	 * 修改功能，再进行修改
	 */
	@ResponseBody
	@RequestMapping("updateToAjax")    // role/updateToAjax
	public Integer updateByRoleAjax(MeetingPub meetingPub){
		
		return meetingPubService.updateByPrimaryKeySelective(meetingPub);
		
	}
	
	/**
	 * 上架和下架功能
     * 修改状态
     * 
     * 0改为1，或由1改为0
     */
	@RequestMapping("updateStatusById")    
	@ResponseBody
	public int updateStatusById(@RequestParam("status")final Integer status,
								@RequestParam("id")final String id){
		return meetingPubService.updateStatusById(status, id);
	
	}
	/**
	 * 添加功能
	 * 
	 */
	@ResponseBody
	@RequestMapping("add")
	public int addRole( MeetingPub meetingPub) {
		
		return meetingPubService.insertSelective(meetingPub);
	}
}


