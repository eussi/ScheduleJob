package com.dcits.schedulejob.utils;

import com.dcits.schedulejob.constants.Constants;
import com.dcits.schedulejob.domain.ScheduleJob;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxueming on 2018/6/18.
 */
public class StringUtils {
    //将存储在数据文件中的字符串转换成实体形式
    public static ScheduleJob string2Domain(String s) {
        String[] fields = s.split(Constants.COMMA);
        if(fields.length!=12) {
            return null;
        }
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobId(fields[0].trim());
        if(!"null".equals(fields[1].trim()))
            scheduleJob.setCreateTime(strToDate(fields[1].trim()));
        if(!"null".equals(fields[2].trim()))
            scheduleJob.setUpdateTime(strToDate(fields[2].trim()));
        scheduleJob.setJobName(fields[3].trim());
        scheduleJob.setJobGroup(fields[4].trim());
        scheduleJob.setJobStatus(fields[5].trim());
        scheduleJob.setCronExpression(fields[6].trim());
        scheduleJob.setDescription(fields[7].trim());
        scheduleJob.setBeanClass(fields[8].trim());
        scheduleJob.setIsConcurrent(fields[9].trim());
        scheduleJob.setSpringId(fields[10].trim());
        scheduleJob.setMethodName(fields[11].trim());

        return scheduleJob;
    }

    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static boolean isNotBlank(String str) {
        str = str.trim();
        if(str!=null && !"".equals(str) && !"null".equals(str))
            return true;
        return false;
    }
}
