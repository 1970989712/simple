package com.zjl.org.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;

import java.util.Date;

/**
 * @author zhoujl
 * @date 2020/3/23
 */
@Table(name="sys_user",description = "用户表")
@MapperXml(namespace="SysUser")
@ContentRowHeight(30)//内容高度
@HeadRowHeight(30)//头部高度
@ColumnWidth(25)//列宽
public class SysUser extends AbstractBean {

    //账号
    @ExcelIgnore
    private String userId;
    //账号
    @ExcelProperty("账户")
    private String account;
    //用户名
    @ExcelProperty("用户名")
    private String userName;
    //用户密码
    @ExcelIgnore
    private String password;
    //上一次登录时间
    @ExcelIgnore
    private Date lastLoginTime;
    //账号是否可用默认为1（可用）
    @ExcelIgnore
    private Boolean enabled=true;
    //创建时间
    @ExcelIgnore
    private Date createTime;
    //创建人
    @ExcelIgnore
    private String createUser;
    //修改人
    @ExcelIgnore
    private String updateUser;
    //修改时间
    @ExcelIgnore
    private Date updateTime;
    //邮箱
    @ExcelIgnore
    private String mailbox;
    //手机号码
    @ExcelIgnore
    private String userPhone;
    //头像地址
    @ExcelIgnore
    private String userAvatar;
    //性别
    @ExcelIgnore
    private String userSex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
