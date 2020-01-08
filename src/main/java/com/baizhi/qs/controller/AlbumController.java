package com.baizhi.qs.controller;

import com.baizhi.qs.entity.Album;
import com.baizhi.qs.service.AlbumService;
import com.baizhi.qs.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("ShowAllAlbums")
    public Map ShowAllAlbums(Integer page,Integer rows){
        Map map = albumService.queryAlbumByPage(page, rows);
        return map;
    }
    @RequestMapping("saveAlbum")
    public Map saveAlbum(String oper, Album album,String [] id){
        HashMap hashMap = new HashMap();
        //添加逻辑
        if(oper.equals("add")){
            String albumId = UUID.randomUUID().toString();
            album.setId(albumId);
            albumService.insert(album);
            hashMap.put("albumId",albumId);
            //修改逻辑
        }else if(oper.equals("edit")){
            albumService.update(album);
            hashMap.put("albumId",album.getId());
            //删除逻辑
        }else{
            albumService.delete(id);
        }
        return hashMap;
    }

    //下载
    @RequestMapping("uploadAlbum")
    public Map uploadAlbum(HttpSession session, MultipartFile cover, String albumId, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/albumImg/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        // 网络路径
        String http = HttpUtil.getHttp(cover, request, "/upload/albumImg/");
        Album album = new Album();
        album.setId(albumId);
        album.setCover(http);
        albumService.update(album);
        hashMap.put("status",200);
        return hashMap;
    }
}
