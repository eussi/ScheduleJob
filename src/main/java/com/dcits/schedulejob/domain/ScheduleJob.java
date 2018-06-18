package com.dcits.schedulejob.domain;

import com.dcits.schedulejob.constants.Constants;
import com.dcits.schedulejob.utils.StringUtils;

import java.util.Date;
/**
 *
 * @Description: 计划任务信息
 * @author wangxueming
 * @date 2018-06-18
 */
public class ScheduleJob {

    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";
    private Long jobId;

    private Date createTime;

    private Date updateTime;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 是否启动任务
     */
    private String jobStatus;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 描述
     */
    private String description;
    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;
    /**
     * 任务是否有状态
     */
    private String isConcurrent;
    /**
     * spring bean
     */
    private String springId;
    /**
     * 任务调用的方法名
     */
    private String methodName;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getSpringId() {
        return springId;
    }

    public void setSpringId(String springId) {
        this.springId = springId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    //将实体转换成存储在数据文件中的字符串形式
    public String domain2String() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getJobId());//job_id
        sb.append(Constants.COMMA);
        if(this.getCreateTime()==null) {
            sb.append("null");
        } else {
            sb.append(StringUtils.dateToStr(this.getCreateTime()));
        }
        sb.append(Constants.COMMA);
        if(this.getUpdateTime()==null) {
            sb.append("null");
        } else {
            sb.append(StringUtils.dateToStr(this.getUpdateTime()));
        }
        sb.append(Constants.COMMA);
        sb.append(this.getJobName());
        sb.append(Constants.COMMA);
        sb.append(this.getJobGroup());
        sb.append(Constants.COMMA);
        sb.append(this.getJobStatus());
        sb.append(Constants.COMMA);
        sb.append(this.getCronExpression());
        sb.append(Constants.COMMA);
        sb.append(this.getDescription());
        sb.append(Constants.COMMA);
        sb.append(this.getBeanClass());
        sb.append(Constants.COMMA);
        sb.append(this.getIsConcurrent());
        sb.append(Constants.COMMA);
        sb.append(this.getSpringId());
        sb.append(Constants.COMMA);
        sb.append(this.getMethodName());
        return sb.toString();
    }
}