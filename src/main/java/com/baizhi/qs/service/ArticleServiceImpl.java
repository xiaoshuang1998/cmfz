package com.baizhi.qs.service;

import com.baizhi.qs.dao.ArticleDao;
import com.baizhi.qs.entity.Article;
import com.baizhi.qs.util.HttpUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map queryAtricleByPage(Integer page, Integer rows) {
        HashMap hashMap = new HashMap();
        int i = articleDao.selectCount(null);
        Integer total = i%rows == 0?i/rows:i/rows+1;
        List<Article> articles = articleDao.selectByRowBounds(null,new RowBounds((page-1)*rows,rows));
        //总条数
        hashMap.put("records",i);
        hashMap.put("total",total);
        hashMap.put("rows",articles);
        hashMap.put("page",page);
        return hashMap;
    }

    @Override
    public void insert(Article article) {
        String uuid = UUID.randomUUID().toString();
        article.setId(uuid);
        article.setCreateDate(new Date());
        article.setPublishDate(new Date());

        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public void delete(String[] id) {
        articleDao.deleteByIdList(Arrays.asList(id));
    }
    @Override
    public Article queryOne(String id) {
        Article article = new Article();
        article.setId(id);
        Article article1 = articleDao.selectOne(article);
        return article1;
    }
}
