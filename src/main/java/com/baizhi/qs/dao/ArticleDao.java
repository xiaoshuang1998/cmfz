package com.baizhi.qs.dao;

import com.baizhi.qs.entity.Article;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleDao extends Mapper<Article> , DeleteByIdListMapper<Article,String>, InsertListMapper<Article> {
}
