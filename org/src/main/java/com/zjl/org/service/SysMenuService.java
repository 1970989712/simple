package com.zjl.org.service;

import com.zjl.comp.service.IBasicBeanService;
import com.zjl.org.bean.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService extends IBasicBeanService<SysMenu> {
    Map founndMenu(String userId,String parentId) throws Exception;
}