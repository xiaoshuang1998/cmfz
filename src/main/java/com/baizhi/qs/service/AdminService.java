package com.baizhi.qs.service;



import javax.servlet.http.HttpSession;
import java.util.Map;


public interface AdminService {
    public Map queryOne(String username, String password, HttpSession session, String clientCode);

}
