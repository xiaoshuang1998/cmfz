package com.baizhi.qs.controller;

import com.baizhi.qs.service.AdminService;
import com.baizhi.qs.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session, String clientCode){
        Map map = adminService.queryOne(username, password,session,clientCode);
        return map;
    }
    // 生成验证码图片
    @RequestMapping("/createImg")
    public void createImg(HttpServletResponse response, HttpSession session) throws IOException {
        CreateValidateCode vcode = new CreateValidateCode();
        String code = vcode.getCode(); // 随机验证码
        System.out.println(code);
        vcode.write(response.getOutputStream()); // 把图片输出client

        // 把生成的验证码 存入session
        session.setAttribute("ServerCode", code);
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Object admin = session.getAttribute("currentAdmin");
        if(admin!=null){
            session.removeAttribute("currentAdmin");
            return "redirect:/jsp/login.jsp";
        }else{
            return "redirect:/jsp/login.jsp";
        }
    }
}
