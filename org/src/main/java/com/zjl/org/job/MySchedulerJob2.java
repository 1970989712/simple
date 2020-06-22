package com.zjl.org.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoujl
 * @date 2020/6/22
 */
public class MySchedulerJob2 implements Job {

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务2: The time is now " + dateFormat().format(new Date()));
    }

}
