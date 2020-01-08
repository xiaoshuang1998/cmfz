package com.baizhi.qs.controller;

import com.alibaba.excel.EasyExcel;
import com.baizhi.qs.entity.Banner;
import com.baizhi.qs.entity.BannerDataListener;
import com.baizhi.qs.entity.BannerDto;
import com.baizhi.qs.service.BannerService;
import com.baizhi.qs.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    //查所有
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Banner> queryAll(){
        List<Banner> list = bannerService.queryAll();
        return list;
    }
    //增删改
    @RequestMapping("/save")
    @ResponseBody
    public Map save(Banner banner,String oper,String[] id){
        HashMap hashMap = new HashMap();
        // 添加逻辑
        if (oper.equals("add")) {
            String bannerId = UUID.randomUUID().toString();
            banner.setId(bannerId);
            bannerService.insert(banner);
            hashMap.put("bannerId", bannerId);
            // 修改逻辑
        } else if (oper.equals("edit")) {
            banner.setUrl(null);
            bannerService.update(banner);
            hashMap.put("bannerId", banner.getId());
            // 删除
        } else {
            bannerService.delete(id);
        }
        return hashMap;
    }
    //分页查
    @RequestMapping("/queryByPage")
    @ResponseBody
    public BannerDto queryByPage(int page,int rows){
        BannerDto bannerDto = bannerService.queryByPage(page, rows);
        return bannerDto;
    }

    //上传
    @RequestMapping("uploadBanner")
    // MultipartFile url(上传的文件),String bannerId(轮播图Id 更新使用)
    public Map uploadBanner(MultipartFile url, String bannerId, HttpSession session, HttpServletRequest request) {

        HashMap hashMap = new HashMap();
        // 获取真实路径
        String realPath = session.getServletContext().getRealPath("/upload/img/");
        // 判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String http = HttpUtil.getHttp(url, request, "/upload/img/");
        // 文件上传 工具类完成
        // 更新数据库信息
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setUrl(http);
        bannerService.update(banner);
        hashMap.put("status", 200);
        return hashMap;
    }
    @RequestMapping("export")
    public void export(){
        bannerService.export();
    }
    @RequestMapping("/importBanner")
    public Map importBanner(MultipartFile inputBanner,HttpServletRequest request){
        String http= HttpUtil.getHttp(inputBanner,request,"/upload/importBanner/");
        String s=http.split("/")[http.split("/").length-1];
        String realPath=request.getSession().getServletContext().getRealPath("/upload/importBanner/");
        String path=realPath+s;
        EasyExcel.read(path,Banner.class,new BannerDataListener()).sheet().doRead();
        HashMap hashMap=new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
}
