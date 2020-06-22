package com.zjl.comp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoujl
 * @date 2020/5/16
 */
public interface LoginUser extends Serializable {

    public String getUserId();

    public String getAccount();

    public String getUserName();

    public String getPassword();

    public Date getLastLoginTime();

    public Boolean getEnabled();

    public String getMailbox();

    public String getUserPhone();

    public String getUserAvatar();

}
