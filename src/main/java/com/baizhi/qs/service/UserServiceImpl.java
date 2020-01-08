package com.baizhi.qs.service;


import com.baizhi.qs.dao.UserDao;
import com.baizhi.qs.entity.MapDto;
import com.baizhi.qs.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void delete(String[] ids) {
        userDao.deleteByIdList(Arrays.asList(ids));
    }

    @Override
    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map queryByPage(int currentPage, int pageSize) {
        HashMap hashMap = new HashMap();
        int count = userDao.selectCount(null);
        hashMap.put("page",currentPage);
        hashMap.put("records",count);
        int total = count%pageSize==0?count/pageSize:count/pageSize+1;
        hashMap.put("total",total);
        List<User> rows = userDao.selectByRowBounds(null, new RowBounds((currentPage - 1) * pageSize, pageSize));
        hashMap.put("rows",rows);
        return hashMap;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer queryBySexAndDay(String sex, Integer day) {
        Integer count = userDao.queryBySexAndDay(sex, day);
        return count;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<MapDto> queryBySexGetLocation(String sex) {
        List<MapDto> mapDtos = userDao.queryBySexGetLocation(sex);
        return mapDtos;
    }
    @Override
    public Map queryByPhone(String phone,String password) {
        User user = new User();
        user.setPhone(phone);
        User user1 = userDao.selectOne(user);
        HashMap hashMap = new HashMap();
        if(user1==null){
            hashMap.put("status",-200);
            hashMap.put("message","用户名不存在");
        }else if(user1.getPassword().equals(password)){
            hashMap.put("status",200);
            hashMap.put("user",user1);
        }else{
            hashMap.put("status",-200);
            hashMap.put("message","密码错误");
        }
        return hashMap;
    }
}
