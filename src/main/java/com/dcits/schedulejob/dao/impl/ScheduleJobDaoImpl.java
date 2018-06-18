package com.dcits.schedulejob.dao.impl;

import com.dcits.schedulejob.constants.Constants;
import com.dcits.schedulejob.dao.DomainDao;
import com.dcits.schedulejob.domain.ScheduleJob;
import com.dcits.schedulejob.utils.FileUtils;
import com.dcits.schedulejob.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;

/**
 * Created by wangxueming on 2018/6/18.
 */
@Repository
public class ScheduleJobDaoImpl implements DomainDao<ScheduleJob>{
    private static File dbFile;
    static{
        dbFile = new File(ScheduleJobDaoImpl.class.getClassLoader().getResource("").getPath() + "job.db");
    }

    public static void main(String [] args) {
        String aa = "";
        StringBuffer sb = new StringBuffer();
        aa = null;
        sb.append(aa);
        sb.append("bbb");
        System.out.println(sb.toString());
    }
    @Override
    public int deleteByPrimaryKey(String jobId) {
        int returnValue = 0;//返回删除的记录数
        List<String> records = FileUtils.getFileContent(dbFile);
        StringBuffer content = new StringBuffer();
        for(String s : records) {
            String id = s.split(",")[0].trim();
            if(!id.equals(jobId)) {
                content.append(s);
                content.append(Constants.NEWLINE);
            } else {
                returnValue++;
            }
        }
        FileUtils.write2File(dbFile, content.toString(), Constants.FILEENCODING);
        return returnValue;
    }

    @Override
    public int insert(ScheduleJob record) {
        List<String> records = FileUtils.getFileContent(dbFile);
        String jobId = UUID.randomUUID().toString().replace("-", "");
        record.setJobId(jobId);
        Set<String> jobIds = this.getJobIds(records);//得到所有记录的主键
        if(jobIds.contains(record.getJobId())) {
            return 0;
        }
        records.add(record.domain2String());
        StringBuffer content = new StringBuffer();
        for(String s : records) {
            content.append(s);
            content.append(Constants.NEWLINE);
        }
        FileUtils.write2File(dbFile, content.toString(), Constants.FILEENCODING);
        return 1;
    }

    @Override
    public int insertSelective(ScheduleJob record) {
        List<String> records = FileUtils.getFileContent(dbFile);
        String jobId = UUID.randomUUID().toString().replace("-", "");
        record.setJobId(jobId);
        Set<String> jobIds = this.getJobIds(records);//得到所有记录的主键
        if(jobIds.contains(record.getJobId())) {
            return 0;
        }
        records.add(record.domain2String());
        StringBuffer content = new StringBuffer();
        for(String s : records) {
            content.append(s);
            content.append(Constants.NEWLINE);
        }
        FileUtils.write2File(dbFile, content.toString(), Constants.FILEENCODING);
        return 1;
    }

    @Override
    public ScheduleJob selectByPrimaryKey(String jobId) {
        List<String> records = FileUtils.getFileContent(dbFile);
        for(String s : records) {
            String id = s.split(",")[0].trim();
            if(id.equals(jobId)) {
               return StringUtils.string2Domain(s);
            }
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ScheduleJob record) {
        int returnValue = 0;
        String jobId = record.getJobId();
        List<String> records = FileUtils.getFileContent(dbFile);
        StringBuffer content = new StringBuffer();
        for(String s : records) {
            String id = s.split(",")[0].trim();
            if(!id.equals(jobId)) {
                content.append(s);
                content.append(Constants.NEWLINE);
            } else {
                ScheduleJob scheduleJob = StringUtils.string2Domain(s);
                if(record.getCreateTime()!=null) scheduleJob.setCreateTime(record.getCreateTime());
                if(record.getUpdateTime()!=null) scheduleJob.setUpdateTime(record.getUpdateTime());
                if(record.getJobName()!=null) scheduleJob.setJobName(record.getJobName());
                if(record.getJobGroup()!=null) scheduleJob.setJobGroup(record.getJobGroup());
                if(record.getJobStatus()!=null) scheduleJob.setJobStatus(record.getJobStatus());
                if(record.getCronExpression()!=null) scheduleJob.setCronExpression(record.getCronExpression());
                if(record.getDescription()!=null) scheduleJob.setDescription(record.getDescription());
                if(record.getBeanClass()!=null) scheduleJob.setBeanClass(record.getBeanClass());
                if(record.getIsConcurrent()!=null) scheduleJob.setIsConcurrent(record.getIsConcurrent());
                if(record.getSpringId()!=null) scheduleJob.setSpringId(record.getSpringId());
                if(record.getMethodName()!=null) scheduleJob.setMethodName(record.getMethodName());
                content.append(scheduleJob.domain2String());
                content.append(Constants.NEWLINE);
                returnValue ++ ;
            }
        }
        FileUtils.write2File(dbFile, content.toString(), Constants.FILEENCODING);
        return returnValue;
    }

    @Override
    public int updateByPrimaryKey(ScheduleJob record) {
        int returnValue = 0;
        String jobId = record.getJobId();
        List<String> records = FileUtils.getFileContent(dbFile);
        StringBuffer content = new StringBuffer();
        for(String s : records) {
            String id = s.split(",")[0].trim();
            if(!id.equals(jobId)) {
                content.append(s);
                content.append(Constants.NEWLINE);
            } else {
                ScheduleJob scheduleJob = StringUtils.string2Domain(s);
                if(record.getCreateTime()!=null) scheduleJob.setCreateTime(record.getCreateTime());
                if(record.getUpdateTime()!=null) scheduleJob.setUpdateTime(record.getUpdateTime());
                if(record.getJobName()!=null) scheduleJob.setJobName(record.getJobName());
                if(record.getJobGroup()!=null) scheduleJob.setJobGroup(record.getJobGroup());
                if(record.getJobStatus()!=null) scheduleJob.setJobStatus(record.getJobStatus());
                if(record.getCronExpression()!=null) scheduleJob.setCronExpression(record.getCronExpression());
                if(record.getDescription()!=null) scheduleJob.setDescription(record.getDescription());
                if(record.getBeanClass()!=null) scheduleJob.setBeanClass(record.getBeanClass());
                if(record.getIsConcurrent()!=null) scheduleJob.setIsConcurrent(record.getIsConcurrent());
                if(record.getSpringId()!=null) scheduleJob.setSpringId(record.getSpringId());
                if(record.getMethodName()!=null) scheduleJob.setMethodName(record.getMethodName());
                content.append(scheduleJob.domain2String());
                content.append(Constants.NEWLINE);
                returnValue ++ ;
            }
        }
        FileUtils.write2File(dbFile, content.toString(), Constants.FILEENCODING);
        return returnValue;
    }

    @Override
    public List<ScheduleJob> getAll() {
        List<ScheduleJob> jobs = new ArrayList<ScheduleJob>();
        List<String> records = FileUtils.getFileContent(dbFile);
        for(String s : records) {
            jobs.add(StringUtils.string2Domain(s));
        }
        return jobs;
    }

    private Set<String> getJobIds(List<String> records) {
        Set<String> jobIds = new HashSet<String>();
        for(String s : records) {
            String id = s.split(",")[0].trim();
            jobIds.add(id);
        }
        return jobIds;
    }
}
