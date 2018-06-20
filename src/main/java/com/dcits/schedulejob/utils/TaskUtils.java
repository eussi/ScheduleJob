package com.dcits.schedulejob.utils;

import com.dcits.schedulejob.domain.ScheduleJob;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {
        System.out.println("开始调用[" + scheduleJob.getJobName() + "]任务");
		Object object = null;
		Class clazz = null;
//		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
//			object = SpringUtils.getBean(scheduleJob.getSpringId());
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {//修改将springId作为shell命令使用
            System.out.println("该任务是shell脚本:" + scheduleJob.getSpringId());
//			String results = ExecuteShell.executeShell(scheduleJob.getSpringId().trim(), new String[]{}, "./");//注意此处传new String[]{}代表在这个数组设定的环境中执行脚本
			String results = ExecuteShell.executeShell(scheduleJob.getSpringId().trim(), null, "./");//传null代表在当前环境的子shell中执行，环境变量也是当前环境
            System.out.println("执行结果:" + results);
            System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]----------运行结束");
            return;
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            System.out.println("该任务是类文件:" + scheduleJob.getBeanClass());
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]----------运行结束");
	}
}
