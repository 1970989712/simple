package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_crontask")
@MapperXml(namespace="SysCrontask")
public class SysCrontask extends AbstractBean {

    /** 主键id **/
    private String uuid;

    /** 编号 **/
    private String crontasknum;

    /** 描述 **/
    private String description;

    /** 任务名称 **/
    private String crontaskname;

    /** 类 **/
    private String classname;

    /** 任务计划 **/
    private String taskplan;

    /** 是否激活 **/
    private String active;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    /** 创建人 **/
    private String createUser;

    /**  **/
    private String updateUser;



    public  String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public  String getCrontasknum() {
        return crontasknum;
    }

    public void setCrontasknum(String crontasknum) {
        this.crontasknum = crontasknum;
    }


    public  String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public  String getCrontaskname() {
        return crontaskname;
    }

    public void setCrontaskname(String crontaskname) {
        this.crontaskname = crontaskname;
    }


    public  String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }


    public  String getTaskplan() {
        return taskplan;
    }

    public void setTaskplan(String taskplan) {
        this.taskplan = taskplan;
    }


    public  String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public  Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public  String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public  String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
