package com.baizhi.qs.service;

import com.baizhi.qs.annotation.LogAnnotation;
import com.baizhi.qs.dao.AlbumDao;
import com.baizhi.qs.entity.Album;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map queryAlbumByPage(Integer page, Integer rows) {
        HashMap hashMap = new HashMap();
        int i = albumDao.selectCount(null);
        Integer total = i%rows == 0?i/rows:i/rows+1;
        List<Album> albums = albumDao.selectByRowBounds(null, new RowBounds((page - 1) * rows, rows));
        //总条数
        hashMap.put("records",i);
        hashMap.put("total",total);
        hashMap.put("rows",albums);
        hashMap.put("page",page);
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "")
    public void insert(Album album) {
        albumDao.insert(album);
    }

    @Override
    public void update(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }

    @Override
    public void delete(String[] id) {
        albumDao.deleteByIdList(Arrays.asList(id));
    }

}
