package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_user_group_role")
@MapperXml(namespace="SysUserGroupRole")
public class SysUserGroupRole extends AbstractBean {

    /** 角色用户关联id **/
    private String userGroupRoleId;

    /** 角色id **/
    private String roleId;

    /** 用户id或者部门id **/
    private String userGroupId;

    /** 关联类型 **/
    private String userGroupType;

    /** 创建人 **/
    private String createUser;

    /** 创建时间 **/
    private Date createTime;

    /** 更新人 **/
    private String updateUser;

    /** 更新时间 **/
    private Date updateTime;

    private String userGroupName;

    public  String getUserGroupRoleId() {
        return userGroupRoleId;
    }

    public void setUserGroupRoleId(String userGroupRoleId) {
        this.userGroupRoleId = userGroupRoleId;
    }


    public  String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public  String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }


    public  String getUserGroupType() {
        return userGroupType;
    }

    public void setUserGroupType(String userGroupType) {
        this.userGroupType = userGroupType;
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

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
}
