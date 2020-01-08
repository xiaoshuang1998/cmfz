package com.baizhi.qs.service;

import com.baizhi.qs.entity.Banner;
import com.baizhi.qs.entity.BannerDto;

import java.util.List;

public interface BannerService {
    //查所有
    public List<Banner> queryAll();
    //添加
    public void insert(Banner banner);
    //修改
    public void update(Banner banner);
    //删除
    public void delete(String [] id);
    //导出
    public void export();
    //分页查
    public BannerDto queryByPage(int curPage, int pageSize);
}
