package com.zjl.org.service;

import com.zjl.comp.service.IBasicBeanService;
import com.zjl.comp.util.ZlJson;
import com.zjl.org.bean.SysUser;

import java.util.Map;


public interface UserService extends IBasicBeanService<SysUser> {

    Map login(String account, String password) throws Exception;

    ZlJson logout(String userId);

    Map getInfo();
}