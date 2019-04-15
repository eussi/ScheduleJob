package com.eussi.schedulejob.task;

import com.eussi.schedulejob.domain.ScheduleJob;
import com.eussi.schedulejob.utils.TaskUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 
 * @Description: 计划任务执行处 无状态
 * @author wangxueming
 * @date 2018-06-18
 */
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());


	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}