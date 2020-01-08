package com.baizhi.qs.dao;

import com.baizhi.qs.entity.MapDto;
import com.baizhi.qs.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User>, InsertListMapper<User>, DeleteByIdListMapper<User,String>{
    Integer queryBySexAndDay(@Param("sex") String sex,@Param("day") Integer day);
    List<MapDto> queryBySexGetLocation(String sex);
}
