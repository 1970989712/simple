package com.zjl.comp.conterllor;

import com.zjl.comp.anno.AuthorityKey;
import com.zjl.comp.anno.Table;
import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.bean.AbstractBean;
import com.zjl.comp.exception.MyException;
import com.zjl.comp.service.IBasicBeanService;
import com.zjl.comp.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import com.alibaba.excel.EasyExcel;

/**
 * @author zhoujl
 * @date 2020/3/20
 */
public class BaseConterllor<T extends AbstractBean> {

    @Autowired
    private IBasicBeanService<T> updservice;

    private Class<T> clazz;

    public BaseConterllor() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            clazz = (Class<T>) type;
        }
    }

    @ZjlJson
    @RequestMapping(value={"/id/{id}"},method= RequestMethod.GET)
    public Object queryId(@PathVariable String id, HttpServletRequest request) throws Exception {
        return updservice.queryById(id) ;
    }
    
    @RequestMapping(value={"/query"})
    public Object queryPage(HttpServletRequest request) throws Exception {
        Map requestparamemap = CompUtil.getParameterMap(request);
        String queryName = (String) requestparamemap.get("queryName");
        Integer pagenum = Integer.parseInt((String) requestparamemap.get("pagenum"));
        Integer pagesize = Integer.parseInt((String) requestparamemap.get("pagesize"));
        requestparamemap.remove("queryName");
        requestparamemap.remove("pagenum");
        requestparamemap.remove("pagesize");
        return updservice.queryPage(queryName,pagenum,pagesize,requestparamemap);
    }


    @RequestMapping(value={"/list"})
    public Object querylist(HttpServletRequest request) throws Exception {
        Map requestparamemap = CompUtil.getParameterMap(request);
        String queryName = (String) requestparamemap.get("queryName");
        return updservice.queryWhere(queryName,requestparamemap);
    }


    @AuthorityKey("insert")
    @RequestMapping(value={"/add"},method= RequestMethod.POST)
    public Object add(HttpServletRequest request,@RequestBody T bean) throws Exception {
        return new ZlJson(ResultCode.ADD_SUCCESS,updservice.insert(bean));
    }

    @AuthorityKey("delete")
    @RequestMapping(value={"/del"},method= RequestMethod.POST)
    public Object del(HttpServletRequest request,@RequestBody T bean) throws Exception {
        return updservice.delete(bean);
    }

    @AuthorityKey("update")
    @RequestMapping(value={"/upd"},method= RequestMethod.POST)
    public Object upd(HttpServletRequest request,@RequestBody T bean) throws Exception {
        return updservice.update(bean);
    }

    @AuthorityKey("update")
    @RequestMapping(value={"/batch/upd"},method= RequestMethod.POST)
    public Object batchupd(HttpServletRequest request,@RequestBody List<T> bean) throws Exception {
        return updservice.batchupd(bean);
    }

    @AuthorityKey("insert")
    @RequestMapping(value={"/batch/add"},method= RequestMethod.POST)
    public Object batchadd(HttpServletRequest request,@RequestBody List<T> bean) throws Exception {
        return updservice.batchadd(bean);
    }

    @AuthorityKey("delete")
    @RequestMapping(value={"/batch/del"},method= RequestMethod.POST)
    public Object batchdel(HttpServletRequest request,@RequestBody List<T> bean) throws Exception {
        return new ZlJson(ResultCode.DEL_SUCCESS,updservice.batchdel(bean));
    }


    @RequestMapping("/export")
    public void exporExcel(HttpServletResponse response) throws Exception {
        String fileName =  "";
        if(clazz.isAnnotationPresent(Table.class)) {
            fileName = clazz.getAnnotation(Table.class).description();
            if(fileName=="") fileName =  clazz.getAnnotation(Table.class).name();
        }
        if(fileName=="") throw new MyException(clazz.toString()+"缺少Table注解");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("filename", URLEncoder.encode(fileName, "UTF-8")+".xlsx");
        EasyExcel.write(response.getOutputStream(),clazz).sheet().doWrite(updservice.getAllData());
    }

}
