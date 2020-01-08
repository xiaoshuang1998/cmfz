package com.baizhi.qs.service;

import com.baizhi.qs.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //分页
    public Map queryChapterById(Integer page,Integer rows,String albumId);
    //添加
    public void insert(Chapter chapter);
    //修改
    public void update(Chapter chapter);
    //删除
    public void delete(String [] id);
}
