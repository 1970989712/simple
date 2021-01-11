package com.zjl.org.config;

import com.zjl.org.service.SysAnonymousService;
import com.zjl.org.service.SysCrontaskService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;


import javax.annotation.Resource;

/**
 * @author zhoujl
 * @date 2020/6/22
 * 当ioc容器加载处理完相应的bean之后，
 * 也给我们提供了一个机会（先有InitializingBean，后有ApplicationListener<ContextRefreshedEvent>），
 * 可以去做一些自己想做的事。其实这也就是spring ioc容器给提供的一个扩展的地方。我们可以这样使用这个扩展机制。
 */
@Configuration
public class MyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    SysCrontaskService sysScheduler;

    @Resource
    SysAnonymousService anonymousService;

    // 防止重复执行
    private static boolean loaded = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!loaded) {
            loaded = true;
            try {
                anonymousService.getAllAnonymous(); //匿名访问清单
                sysScheduler.schedulerJob(); //定时任务加载
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

