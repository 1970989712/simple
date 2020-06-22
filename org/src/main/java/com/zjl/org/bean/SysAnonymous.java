package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_anonymous")
@MapperXml(namespace="SysAnonymous")
public class SysAnonymous extends AbstractBean {

    /** 内部主键 **/
    private String anonymousId;

    /** 应用ID **/
    private String appId;

    /**  **/
    private String url;

    /** ALL,PUT,GET,POST,DELETE,默认为ALL **/
    private String httpmethod;



    public  String getAnonymousId() {
        return anonymousId;
    }

    public void setAnonymousId(String anonymousId) {
        this.anonymousId = anonymousId;
    }


    public  String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    public  String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public  String getHttpmethod() {
        return httpmethod;
    }

    public void setHttpmethod(String httpmethod) {
        this.httpmethod = httpmethod;
    }

}
