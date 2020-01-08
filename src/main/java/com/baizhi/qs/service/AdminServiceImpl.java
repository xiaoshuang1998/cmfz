package com.baizhi.qs.service;

import com.baizhi.qs.dao.AdminDao;
import com.baizhi.qs.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public Map queryOne(String username, String password, HttpSession session,String clientCode) {
        // 获取server上的生成的验证码
        String serverCode = (String) session.getAttribute("ServerCode").toString();
        //1 创建需要返回的Map集合
        HashMap hashMap = new HashMap();
        //2 调方法进行查询
        Admin admin = new Admin();
        admin.setUsername(username);
        Admin admin1 = adminDao.selectOne(admin);
        //进行判断，封装错误信息
        if(!clientCode.equals(serverCode)) {
            hashMap.put("status",400);
            hashMap.put("msg","验证码错误！");
        }else if (admin1 == null){
            hashMap.put("status",400);
            hashMap.put("msg","该用户不存在！");
        }else if(!admin1.getPassword().equals(password)){
            hashMap.put("status",400);
            hashMap.put("msg","密码错误！");
        }else {
            hashMap.put("status",200);
            session.setAttribute("admin",admin1);
        }
        //返回集合
        return hashMap;
    }
}
