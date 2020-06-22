package com.zjl.comp.conterllor;

import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.bean.IBomfBean;
import com.zjl.comp.bean.LoginUser;
import com.zjl.comp.service.IBasicBeanService;
import com.zjl.comp.util.CompUtil;
import com.zjl.comp.util.ResultCode;
import com.zjl.comp.util.ZlJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujl
 * @date 2020/3/20
 */
public class BaseConterllor<T extends IBomfBean> {

    @Autowired
    private IBasicBeanService<T> updservice;

    private Class<T> updaclazz;


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


    @RequestMapping(value={"/add"},method= RequestMethod.POST)
    public Object add(HttpServletRequest request,@RequestBody T bean) throws Exception {
        return new ZlJson(ResultCode.ADD_SUCCESS,updservice.insert(bean));
    }

    @RequestMapping(value={"/del"},method= RequestMethod.POST)
    public Object del(HttpServletRequest request,@RequestBody T bean) throws Exception {
        return updservice.delete(bean);
    }


    @RequestMapping(value={"/upd"},method= RequestMethod.POST)
    public Object upd(HttpServletRequest request,@RequestBody T bean) throws Exception {
        return updservice.update(bean);
    }

    @RequestMapping(value={"/batch/upd"},method= RequestMethod.POST)
    public Object batchupd(HttpServletRequest request,@RequestBody List<T> bean) throws Exception {
        return updservice.batchupd(bean);
    }

    @RequestMapping(value={"/batch/add"},method= RequestMethod.POST)
    public Object batchadd(HttpServletRequest request,@RequestBody List<T> bean) throws Exception {
        return updservice.batchadd(bean);
    }

    @RequestMapping(value={"/batch/del"},method= RequestMethod.POST)
    public Object batchdel(HttpServletRequest request,@RequestBody List<T> bean) throws Exception {
        return new ZlJson(ResultCode.DEL_SUCCESS,updservice.batchdel(bean));
    }

}
