package com.zjl.comp.service;

import com.github.pagehelper.PageInfo;
import com.zjl.comp.util.ZlJson;

import java.util.List;
import java.util.Map;

public interface IBasicBeanService<T> {

    List<T> getAllData() throws Exception;

    T queryById(String pk) throws Exception;

    List<T> queryWhere(Map map) throws Exception;

    PageInfo<T> queryPage(String queryName,Integer pagenum, Integer pagesize, Map map) throws Exception;

    T insert(T t) throws Exception;

    ZlJson delete(T t) throws Exception;

    ZlJson update(T t) throws Exception;

    Integer batchupd(List<T> list) throws Exception;

    Integer batchadd(List<T> list) throws Exception;

    Integer batchdel(List<T> list) throws Exception;

    T queryOne(Map<String, Object> parameter);

}
