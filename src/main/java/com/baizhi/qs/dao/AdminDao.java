package com.baizhi.qs.dao;

import com.baizhi.qs.entity.Admin;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface AdminDao extends Mapper<Admin>, DeleteByIdListMapper<Admin,String>,InsertListMapper<Admin>{

}
