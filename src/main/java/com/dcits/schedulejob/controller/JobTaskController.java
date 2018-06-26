package com.dcits.schedulejob.controller;


import com.dcits.schedulejob.domain.RetObj;
import com.dcits.schedulejob.domain.ScheduleJob;
import com.dcits.schedulejob.service.JobTaskService;
import com.dcits.schedulejob.utils.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/task")
public class JobTaskController {
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private JobTaskService taskService;

	@RequestMapping("taskList")
	public String taskList(HttpServletRequest request) {
		List<ScheduleJob> taskList = taskService.getAllTask();
		request.setAttribute("taskList", taskList);
		String path = request.getContextPath();
		String rootPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ "/";
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		request.setAttribute("basePath", basePath);
		request.setAttribute("rootPath", rootPath);
		request.setAttribute("newLineChar", "\n");
		return "base/task/taskList";
	}

	@RequestMapping("add")
	@ResponseBody
	public RetObj taskList(HttpServletRequest request, ScheduleJob scheduleJob) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			retObj.setMsg("cron表达式有误，不能被解析！");
			return retObj;
		}
		Object obj = null;
        boolean isShell = false;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
				//obj = SpringUtils.getBean(scheduleJob.getSpringId());
                isShell = true;
			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass());
				obj = clazz.newInstance();
			}
		} catch (Exception e) {
			// do nothing.........
		}
        if(!isShell) {
            if (obj == null) {
                retObj.setMsg("未找到目标类！");
                return retObj;
            } else {
                Class clazz = obj.getClass();
                Method method = null;
                try {
                    method = clazz.getMethod(scheduleJob.getMethodName(), null);
                } catch (Exception e) {
                    // do nothing.....
                }
                if (method == null) {
                    retObj.setMsg("未找到目标方法！");
                    return retObj;
                }
            }
        }
		try {
            scheduleJob.setJobStatus("0");//初始化状态设置为0，已停止状态
			taskService.addTask(scheduleJob);
		} catch (Exception e) {
			e.printStackTrace();
			retObj.setFlag(false);
			retObj.setMsg("保存失败，检查 name group 组合是否有重复！");
			return retObj;
		}

		retObj.setFlag(true);
		return retObj;
	}

	@RequestMapping("changeJobStatus")
	@ResponseBody
	public RetObj changeJobStatus(HttpServletRequest request, String jobId, String cmd) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			taskService.changeStatus(jobId, cmd);
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			retObj.setMsg("任务状态改变失败！");
			return retObj;
		}
		retObj.setFlag(true);
		return retObj;
	}

	@RequestMapping("updateCron")
	@ResponseBody
	public RetObj updateCron(HttpServletRequest request, String jobId, String cron) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		} catch (Exception e) {
			retObj.setMsg("cron表达式有误，不能被解析！");
			return retObj;
		}
		try {
			taskService.updateCron(jobId, cron);
		} catch (SchedulerException e) {
			retObj.setMsg("cron更新失败！");
			return retObj;
		}
		retObj.setFlag(true);
		return retObj;
	}
}
