package com.baizhi.qs.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class HttpUtil {
    public static String getHttp(MultipartFile file, HttpServletRequest request,String path){
        //获取真事路径
        String realPath = request.getSession().getServletContext().getRealPath(path);
        //判断文件是否存在
        File file1 = new File(realPath);
        if(!file1.exists()) {
            file1.mkdirs();
        }
        //防止重名
        String NewFileName = new Date().getTime()+"_"+ file.getOriginalFilename();

        //获取http协议头
        String scheme = request.getScheme();
        //获取IP地址
        String localHost = null;
        try {
             localHost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //获取端口号
        int serverPort = request.getServerPort();
        //获取项目名
        String contextPath = request.getContextPath();
        //
        String uri = scheme+"://"+localHost.split("/")[1]+":"+serverPort + contextPath + path + NewFileName;
        try {
            file.transferTo(new File(realPath,NewFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }
}
