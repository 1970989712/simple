package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_resource_authority")
@MapperXml(namespace="SysResourceAuthority")
public class SysResourceAuthority extends AbstractBean {

    /** 资源权限id **/
    private String resourceAuthorityId;

    /** 角色id **/
    private String roleId;

    /** 资源id **/
    private String resourceId;

    /** 资源类型 **/
    private String resourceType;

    /** 创建人 **/
    private String createUser;

    /** 创建时间 **/
    private Date createTime;

    /** 更新人 **/
    private String updateUser;

    /** 更新时间 **/
    private Date updateTime;



    public  String getResourceAuthorityId() {
        return resourceAuthorityId;
    }

    public void setResourceAuthorityId(String resourceAuthorityId) {
        this.resourceAuthorityId = resourceAuthorityId;
    }


    public  String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public  String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public  String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }


    public  String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public  String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    public  Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
