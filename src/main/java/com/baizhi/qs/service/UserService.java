package com.baizhi.qs.service;

import com.baizhi.qs.entity.MapDto;
import com.baizhi.qs.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void insert(User user);

    public void delete(String[] ids);

    public void update(User user);

    public Map queryByPage(int currentPage,int pageSize);

    public Integer queryBySexAndDay(String sex,Integer day);

    public List<MapDto> queryBySexGetLocation(String sex);

    public Map queryByPhone(String phone,String password);
}
