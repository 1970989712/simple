package com.zjl.org.service;

import com.zjl.comp.service.IBasicBeanService;
import com.zjl.org.bean.SysCrontask;

public interface SysCrontaskService extends IBasicBeanService<SysCrontask> {

    void schedulerJob() throws Exception;

}