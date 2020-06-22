package com.zjl.org.service;

import com.zjl.comp.service.IBasicBeanService;
import com.zjl.org.bean.SysAnonymous;

public interface SysAnonymousService extends IBasicBeanService<SysAnonymous> {
    void getAllAnonymous() throws Exception;
}