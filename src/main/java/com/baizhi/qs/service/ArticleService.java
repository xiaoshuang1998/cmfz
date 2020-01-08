package com.baizhi.qs.service;

import com.baizhi.qs.entity.Article;

import java.util.Map;

public interface ArticleService {
    //分页
    public Map queryAtricleByPage(Integer page, Integer rows);
    //添加
    public void insert(Article article);
    //修改
    public void update(Article article);
    //删除
    public void delete(String [] id);
    //查所有
    public Article queryOne(String id);
}
