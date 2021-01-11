package com.zjl.comp.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.IBomfBean;
import com.zjl.comp.dao.util.BeanDaoHelper;
import com.zjl.comp.exception.MyException;
import com.zjl.comp.util.*;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;


public class BasicBeanDao<T extends IBomfBean> implements IBeanDao<T> {

    /**
     * SqlSessionTemplate是Mybatis
     * 为了接入Spring提供的Bean。
     */
    @Autowired
    private SqlSessionTemplate sqlTemplate; //mybatis 提供的线程安全的

    private String defaultQuery; //每个bean 需要此方法

    private String Tablename;

    private String namespace;

    private ResultMapping idMapping;

    private  List<ResultMapping> resultMappings;

    private Class<T> clazz;

    private String userId;

    public SqlSessionTemplate getSqlTemplate(){
        return sqlTemplate;
    }

    public BasicBeanDao() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            clazz = (Class<T>) type;
            getbeananno(clazz);
        }
    }

    @Override
    public T queryById(String pk) throws Exception {
        getMapperConfigure();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put(idMapping.getProperty(),pk);
        return sqlTemplate.selectOne(namespace+defaultQuery,parameter);
    }

    @Override
    public List<T> querywhere(Map<String, Object> parameter) throws Exception {
        return sqlTemplate.selectList(namespace+defaultQuery,parameter);
    }

    @Override
    public List<T> querywhere(String statement,Map<String, Object> parameter) throws Exception {
        return sqlTemplate.selectList(namespace+statement,parameter);
    }

    @Override
    public List<T> querywhere(String namespace,String statement,Map<String, Object> parameter) throws Exception {
        return sqlTemplate.selectList(namespace+"."+statement,parameter);
    }

    @Override
    public T queryOne(Map<String, Object> parameter){
        return sqlTemplate.selectOne(namespace+defaultQuery,parameter);
    }

    @Override
    public ZlJson delete(T t) throws Exception {
        getMapperConfigure();
        Map parameter =  BeanMap.create(t);
        Map m = new HashMap();
        String id = (String) parameter.get(idMapping.getProperty());
        Map hash = new HashMap();
        hash.put("key", idMapping.getColumn());
        hash.put("value",id);
        List list = new ArrayList<>();
        list.add(hash);
        preDelete(parameter,t);
        m.put("table",Tablename);
        m.put("requst",list);
        int i = sqlTemplate.delete("myBasedao.deletebasebean",m);
        afterDelete(parameter,t);
        if(i>0) {
            return new ZlJson();
        }else{
            return new ZlJson(ResultCode.COMMON_FAIL,t);
        }
    }

    @Override
    public T insert(T t) throws Exception {
        userId = LoginInfo.getUser().getUserId();
        getMapperConfigure();
        preInsert(t);
        Map parameter =  BeanMap.create(t);
        List<ResultMapping> mappings = resultMappings;
        List<Map> list = new ArrayList<>();
        Map m = new HashMap();
        Set<String> s = parameter.keySet();
        Map defaultInsertValue = new HashMap();
        boolean idf = true;
        defaultInsertValue.put("create_time",true);
        defaultInsertValue.put("create_user",true);
        if(null==parameter.get(idMapping.getProperty())||""==parameter.get(idMapping.getProperty()))idf = false;
        if(s.contains("createTime")){
            if(null==parameter.get("createTime")||""==parameter.get("createTime"))defaultInsertValue.put("create_time",false);
        }
        if(s.contains("createUser")){
            if(null==parameter.get("createUser")||""==parameter.get("createUser"))defaultInsertValue.put("create_user",false);
        }
        defaultInsertValue.put("id",idf);
        for (String key : s) {
            List<ResultMapping> listkey = mappings.stream().filter((ResultMapping mapping)->key.equals(mapping.getProperty())).collect(Collectors.toList());
            if(listkey.size()==0) continue;
            Map hash = new HashMap();
            hash.put("key", listkey.get(0).getColumn());
            hash.put("value",parameter.get(key));
            list.add(hash);
        }
        createDefaultInsertValue(list,defaultInsertValue,parameter);
        m.put("table",Tablename);
        m.put("requst",list);
        T t1 = (T) CompUtil.mapToObject(parameter,clazz);
        int i = sqlTemplate.insert("myBasedao.insertbasebean",m);
        afterInsert(userId,parameter,t1);
        if(i>0) {
            return t1;
        }else{
            throw new MyException(ResultCode.ADD_FAIL);
        }
    }

    @Override
    public ZlJson update(T t) throws Exception {
        getMapperConfigure();
        Map parameter =  BeanMap.create(t);
        List<ResultMapping> mappings = resultMappings;
        List list = new ArrayList<>();
        Map m = new HashMap();
        String id = (String) parameter.get(idMapping.getProperty());
        m.put("beanidkey",idMapping.getColumn());
        m.put("beanidvalue",id);
        Set<String> s = parameter.keySet();
        for (String key : s) {
            List<ResultMapping> listkey = mappings.stream().filter((ResultMapping mapping)->key.equals(mapping.getProperty())).collect(Collectors.toList());
            if(key.equals(idMapping.getProperty())) continue;
            if(listkey.size()==0) continue;
            Map hash = new HashMap();
            hash.put("key", listkey.get(0).getColumn());
            hash.put("value",parameter.get(key));
            list.add(hash);
        }
        m.put("table",Tablename);
        m.put("requst",list);
        preUpdate(parameter,t);
        int i = sqlTemplate.insert("myBasedao.updatebsaebean",m);
        afterUpdate(parameter,t);
        if(i>0) {
            return new ZlJson(ResultCode.UPD_SUCCESS,t);
        }else{
            return new ZlJson(ResultCode.UPD_FAIL,t);
        }
    }


    @Override
    public PageInfo<T> queryPage(String queryName,Integer pagenum,Integer pagesize,Map map) throws Exception {
        PageHelper.startPage(pagenum, pagesize);
        List<T> tList  =  this.querywhere(queryName,map);
        PageInfo<T> pageInfo = new PageInfo<>(tList);
        return pageInfo;
    }


    /**
     * 自定义批量增删改
     * @param sqlType sql 类型
     * @param listmap 对象集合
     * @return
     */
    public final Integer customizeAllBatch(String sqlType,List<T> listmap){
        SqlSession batchSqlSession = sqlTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        Integer index;
        try {
            for (index=0; index < listmap.size(); index++) {
                T t  = listmap.get(index);
                switch (sqlType){
                    case "insert":
                        insert(t);
                        break;
                    case "update":
                        update(t);
                        break;
                    case "delete":
                        delete(t);
                        break;
                }
            }
            batchSqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            batchSqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            if (batchSqlSession != null) {
                batchSqlSession.close();
            }
        }
        return index;
    }


    private void getbeananno(Class aclazz){
        if(aclazz.isAnnotationPresent(MapperXml.class)) {
            MapperXml mapperxml = (MapperXml)aclazz.getAnnotation(MapperXml.class);
            namespace =mapperxml.namespace()+".";
            defaultQuery = mapperxml.defaultQuery();
            if(namespace.equals("")) throw new MyException(aclazz.toString()+"MapperXml注解缺少namespace");
        }else {
            throw new MyException(aclazz.toString()+"缺少MapperXml注解");
        }
        if(aclazz.isAnnotationPresent(Table.class)){
            Table table = (Table) aclazz.getAnnotation(Table.class);
            Tablename = table.name();
            if(Tablename.equals("")) throw new MyException(aclazz.toString()+"Tablename注解缺少name");
        }else{
            throw new MyException(aclazz.toString()+"缺少Table注解");
        }
    }


    private void getMapperConfigure() {
        org.apache.ibatis.session.Configuration c = sqlTemplate.getConfiguration();
        ResultMap map = c.getResultMap(namespace+"BaseResultMap");
        idMapping = map.getIdResultMappings().get(0);
        resultMappings = map.getResultMappings();   //所有Result
    }


    private void createDefaultInsertValue(List<Map> list,Map map,Map bM){
        Boolean idfalg = (Boolean)map.get("id");
        Boolean falgtime = (Boolean)map.get("create_time");
        Boolean falguser = (Boolean)map.get("create_user");

        Map m = new HashMap();
        if(!idfalg) {
           String id = UUIDUtil.getUUID();
           m.put("key", idMapping.getColumn());
           m.put("value", id);
           for(Map mp:list){
               if(mp.get("key").equals(m.get("key"))) {
                   list.remove(mp);
                   break;
               }
           }
           list.add(m);
           bM.put(idMapping.getProperty(),id);
        }
        if(!falgtime){
            m = new HashMap();
            String tim = DateUtil.format(new Date());
            List<ResultMapping> listkey = resultMappings.stream().filter((ResultMapping mapping)->("createTime").equals(mapping.getProperty())).collect(Collectors.toList());
            m.put("key",listkey.get(0).getColumn());
            m.put("value",tim);
            for(Map mp:list){
                if(mp.get("key").equals(m.get("key"))) {
                    list.remove(mp);
                    break;
                }
            }
            list.add(m);
            bM.put("createTime",DateUtil.parse(tim));
        }
        if(!falguser){
            List<ResultMapping> listkey = resultMappings.stream().filter((ResultMapping mapping)->("createUser").equals(mapping.getProperty())).collect(Collectors.toList());
            m = new HashMap();
            m.put("key",listkey.get(0).getColumn());
            m.put("value",userId);
            for(Map mp:list){
                if(mp.get("key").equals(m.get("key"))) {
                    list.remove(mp);
                    break;
                }
            }
            list.add(m);
            bM.put("createUser",userId);
        }
    }


    @Override
    public void afterDelete(Map<String, Object> params, T bean) throws Exception {

    }

    @Override
    public void afterInsert(String loginUserId,Map<String, Object> params, T bean) throws Exception {

    }

    @Override
    public void afterUpdate(Map<String, Object> params, T bean) throws Exception {

    }

    @Override
    public void preDelete(Map<String, Object> params, T bean) throws Exception {

    }

    @Override
    public void preInsert(T bean) throws Exception {

    }

    @Override
    public void preUpdate(Map<String, Object> params, T bean) throws Exception {

    }

}
