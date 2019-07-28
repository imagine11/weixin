package com.qfjy.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qfjy.project.meeting.bean.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
  
    List<Role> listByRole(Role role);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatchByIds(Integer[] ids);
   
    @Update("update role set status=#{arg0} where id=#{arg1}")
    int updateStatusById(Integer status,Integer id);
}