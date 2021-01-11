package com.zjl.comp.dao;

import com.github.pagehelper.PageInfo;
import com.zjl.comp.bean.IBomfBean;
import com.zjl.comp.util.ZlJson;

import java.util.List;
import java.util.Map;

public interface IBeanDao<T> {

      void afterDelete(Map<String, Object> params, T bean) throws Exception;

      void afterInsert(String loginUserId,Map<String, Object> params, T bean) throws Exception;

      void afterUpdate(Map<String, Object> params, T bean) throws Exception;

      void preDelete(Map<String, Object> params, T bean) throws Exception;

      void preInsert(T bean) throws Exception;

      void preUpdate(Map<String, Object> params, T bean) throws Exception;

      T queryById(String pk) throws Exception;

      List<T> querywhere(Map<String, Object> parameter) throws Exception;

      PageInfo<T> queryPage(String queryName,Integer pagenum, Integer pagesize, Map map) throws Exception;

      List<T> querywhere(String statement,Map<String, Object> parameter) throws Exception;

      List<T> querywhere(String namespace,String statement,Map<String, Object> parameter) throws Exception;

      T insert(T t) throws Exception;

      ZlJson delete(T t) throws Exception;

      ZlJson update(T t) throws Exception;

      Integer customizeAllBatch(String sqlType, List<T> listmap);

      T queryOne(Map<String, Object> parameter);
}
