package com.baizhi.qs.service;

import com.alibaba.excel.EasyExcel;
import com.baizhi.qs.dao.BannerDao;
import com.baizhi.qs.entity.Banner;
import com.baizhi.qs.entity.BannerDto;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService{
    @Autowired
    BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Banner> queryAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }
    //添加
    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }
    //修改
    @Override
    public void update(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }
    //删除
    @Override
    public void delete(String [] id) {
        bannerDao.deleteByIdList(Arrays.asList(id));
    }

    @Override
    public BannerDto queryByPage(int curPage, int pageSize) {
        //创建dto对象
        BannerDto bannerDto = new BannerDto();
        //设置当前页
        bannerDto.setPage(curPage);
        //设置总行数
        int count = bannerDao.selectCount(null);
        bannerDto.setRecords(count);
        //设置总页数
        bannerDto.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
        //计算下标
        int index = (curPage-1)*pageSize;
        //调用dao层的分页查方法 获取当前页数据
        List<Banner> banners = bannerDao.selectByRowBounds(null, new RowBounds(index, pageSize));
        //设置当前页的所有数据
        bannerDto.setRows(banners);
        return bannerDto;
    }
    //导出轮播图信息
    public void export(){
        List<Banner> list = bannerDao.selectAll();
        String fileName = "D:\\百知教育\\第三阶段：框架\\后期项目\\aa\\Excel\\"+new Date().getTime()+".xls";

        EasyExcel.write(fileName, Banner.class) // 指定文件导出的路径及样式
                .sheet("测试")           // 指定导出到哪个sheet工作簿
                .doWrite(list);

    }
}
