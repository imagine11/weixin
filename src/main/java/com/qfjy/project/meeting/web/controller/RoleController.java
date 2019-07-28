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

import com.qfjy.project.meeting.bean.Role;
import com.qfjy.project.meeting.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	/**
	 * 查询列表
	 */
	@RequestMapping("list")
	public ModelAndView selectByRole(Role role) {
		
		ModelAndView model = new ModelAndView();
		List<Role> list = roleService.listByRole(role);
		model.addObject("roleFrom", role);
		model.addObject("listRole",list);
		model.setViewName("/pages/manager/role/role_list.jsp");
		return model;
	}
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}",method=RequestMethod.POST)
	@ResponseBody
	public int deleteByPrimaryKey(@PathVariable("id")final Integer id) {
	
		return roleService.deleteByPrimaryKey(id);
	}
	/**
	 * 根据多个id主键,进行批量删除 
	 */
	@RequestMapping("batch/{ids}")
	@ResponseBody
	public int deleteBatchByIds(@PathVariable("ids")String ids) {
		
		String[] arr = ids.split(",");
		Integer[] ids1 = new Integer[arr.length];
		for(int i = 0;i<arr.length;i++) {
			ids1[i]=Integer.parseInt(arr[i]);
		}
		return roleService.deleteBatchByIds(ids1);
	}
	
	/**
	 * 修改功能，先根据ID进行查询
	 */
	@RequestMapping("selectById")   // role/selectById?id=1
	public ModelAndView  selectById(@RequestParam("id")Integer id){
		ModelAndView model=new ModelAndView();
		Role role=roleService.selectByPrimaryKey(id);
		model.setViewName("/pages/manager/role/role_edit.jsp");
		model.addObject("role", role);
		return model;
	}
	
	/**
	 * 修改功能，再进行修改
	 */
	@RequestMapping("updateTo")    // role/updateTo
	public ModelAndView updateByRole(Role role){
		ModelAndView model=new ModelAndView();
		roleService.updateByPrimaryKeySelective(role);
		model.setViewName("redirect:/role/list");
		return model;
		
	}
	/**
	 * 修改功能，再进行修改
	 */
	@ResponseBody
	@RequestMapping("updateToAjax")    // role/updateToAjax
	public Integer updateByRoleAjax(Role role){
		
		return roleService.updateByPrimaryKeySelective(role);
		
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
								@RequestParam("id")final Integer id){
		return roleService.updateStatusById(status, id);
	
	}
	/**
	 * 添加功能
	 * 
	 */
	@ResponseBody
	@RequestMapping("add")
	public int addRole( Role role) {
		
		return roleService.insertSelective(role);
	}
}
