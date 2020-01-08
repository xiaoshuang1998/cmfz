package com.baizhi.qs.service;

import com.baizhi.qs.entity.Album;

import java.util.Map;

public interface AlbumService {
    //分页
    public Map queryAlbumByPage(Integer page,Integer rows);
    //添加
    public void insert(Album album);
    //修改
    public void update(Album album);
    //删除
    public void delete(String [] id);
}
