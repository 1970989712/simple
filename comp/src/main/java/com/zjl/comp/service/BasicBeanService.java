package com.zjl.comp.service;

import com.github.pagehelper.PageInfo;
import com.zjl.comp.bean.IBomfBean;
import com.zjl.comp.dao.IBeanDao;
import com.zjl.comp.util.ZlJson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BasicBeanService<T extends IBomfBean> implements IBasicBeanService<T> {

    @Autowired
    private IBeanDao<T> updateDao; //调用的dao


    @Override
    public List<T> getAllData() throws Exception {
        return updateDao.querywhere(null);
    }

    @Override
    public T queryById(String pk) throws Exception {
        return updateDao.queryById(pk);
    }

    @Override
    public List<T> queryWhere(Map map) throws Exception {
        return updateDao.querywhere(map);
    }

    @Override
    public List<T> queryWhere(String queryName,Map map) throws Exception {
        return updateDao.querywhere(queryName,map);
    }

    @Override
    public PageInfo<T> queryPage(String queryName,Integer pagenum, Integer pagesize, Map map) throws Exception {
        return updateDao.queryPage(queryName,pagenum,pagesize,map);
    }

    @Override
    public  T insert(T t) throws Exception {
        return updateDao.insert(t);
    }

    @Override
    public ZlJson delete(T t) throws Exception {
        return updateDao.delete(t);
    }

    @Override
    public ZlJson update(T t) throws Exception {
        return updateDao.update(t);
    }

    @Override
    public Integer batchupd(List<T> list) throws Exception {
        return updateDao.customizeAllBatch("update",list);
    }

    @Override
    public Integer batchadd(List<T> list) throws Exception {
        return updateDao.customizeAllBatch("insert",list);
    }

    @Override
    public Integer batchdel(List<T> list) throws Exception {
        return updateDao.customizeAllBatch("delete",list);
    }

    @Override
    public T queryOne(Map<String, Object> parameter) {
        return updateDao.queryOne(parameter);
    }


}
