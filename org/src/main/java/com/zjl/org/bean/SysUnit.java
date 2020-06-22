package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_unit")
@MapperXml(namespace="SysUnit")
public class SysUnit extends AbstractBean {

    /** 部门id **/
    private String unitId;

    /** 部门编码 **/
    private String unitCode;

    /** 部门名称 **/
    private String unitName;

    /** 部门全称 **/
    private String unitFullName;

    /** 部门类型 **/
    private String unitType;

    /** 父部门id **/
    private String parentId;

    /** 排序 **/
    private Integer sortNo;

    /** 是否有效 **/
    private Boolean valid;

    /** 启用时间 **/
    private Date enabledTime;

    /** 创建时间 **/
    private Date createTime;

    /** 创建人 **/
    private String createUser;

    /** 更新时间 **/
    private Date updateTime;

    /** 修改人 **/
    private String updateUser;

    public  String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }


    public  String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }


    public  String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


    public  String getUnitFullName() {
        return unitFullName;
    }

    public void setUnitFullName(String unitFullName) {
        this.unitFullName = unitFullName;
    }


    public  String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }


    public  String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public  Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    public  Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }


    public  Date getEnabledTime() {
        return enabledTime;
    }

    public void setEnabledTime(Date enabledTime) {
        this.enabledTime = enabledTime;
    }


    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public  String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public  Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public  String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


}
