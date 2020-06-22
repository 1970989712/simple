package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_user_dept")
@MapperXml(namespace="SysUserDept")
public class SysUserDept extends AbstractBean {

    /** 用户部门id **/
    private String userDeptId;

    /** 用户id **/
    private String userId;

    /** 部门id **/
    private String deptId;

    /** 是否主要部门 **/
    private Boolean main;

    /** 是否有效 **/
    private Boolean valid;

    /** 失效时间 **/
    private Date timeEnd;

    /** 创建时间 **/
    private Date createTime;

    /** 创建人 **/
    private String createUser;

    /** 更新时间 **/
    private Date updateTime;

    /** 更新人 **/
    private String updateUser;



    public  String getUserDeptId() {
        return userDeptId;
    }

    public void setUserDeptId(String userDeptId) {
        this.userDeptId = userDeptId;
    }


    public  String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public  String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }


    public  Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }


    public  Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }


    public  Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
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
