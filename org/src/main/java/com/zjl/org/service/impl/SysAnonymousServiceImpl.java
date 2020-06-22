package com.zjl.org.service.impl;

import com.zjl.comp.service.BasicBeanService;
import com.zjl.comp.util.RedisServiceUnit;
import com.zjl.org.bean.SysAnonymous;
import com.zjl.org.service.SysAnonymousService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("orgSysAnonymous")
public class SysAnonymousServiceImpl extends BasicBeanService<SysAnonymous> implements SysAnonymousService {

    @Autowired
    private RedisServiceUnit redisUnit;

    private Logger log = LoggerFactory.getLogger(SysAnonymousServiceImpl.class);

    public void getAllAnonymous() throws Exception {
        redisUnit.remove("anonymous");
        List<SysAnonymous> lists = this.queryWhere(null);
        redisUnit.set("anonymous",lists);
        log.info("匿名访问清单加载完成！");
    }


}