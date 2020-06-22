package com.zjl.org.service.impl;

import com.zjl.comp.service.BasicBeanService;
import com.zjl.comp.util.DateUtil;
import com.zjl.org.bean.SysCrontask;
import com.zjl.org.service.SysCrontaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("orgSysCrontask")
public class SysCrontaskServiceImpl extends BasicBeanService<SysCrontask> implements SysCrontaskService {

    private static Logger log = LoggerFactory.getLogger(SysCrontaskServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public void schedulerJob() throws Exception {
        addAllJob();
        scheduler.start();
    }

    private void addAllJob() throws Exception {
        Map m = new HashMap();
        m.put("active","Y");
        List<SysCrontask> listtask = this.queryWhere(m);
        if(listtask.size()==0){
            log.info("没有配置后台定期工作计划!");
            return;
        }
        listtask.forEach((e)->{
            try {
                addJob(e);
            } catch (Exception e1) {
                e1.printStackTrace();
                log.info("后台任务定义异常");
            }
        });
    };

    private void addJob(SysCrontask e) throws SchedulerException, ClassNotFoundException {
        Class<? extends Job> cls = (Class<? extends Job>) Class.forName(e.getClassname());
        JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(e.getCrontaskname()).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(e.getTaskplan());
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger."+e.getCrontaskname())
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        log.info(e.getCrontaskname() + " 将在 " + DateUtil.format(trigger.getNextFireTime()) + " 运行...\r\n");
    }

}