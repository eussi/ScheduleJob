package com.dcits.test;

import com.dcits.schedulejob.dao.DomainDao;
import com.dcits.schedulejob.dao.impl.ScheduleJobDaoImpl;
import com.dcits.schedulejob.domain.ScheduleJob;

import java.util.List;
import java.util.UUID;

/**
 * Created by wangxueming on 2018/6/18.
 */
public class DaoTest {
    public static void main(String[] args) {
//        DomainDao dd = new ScheduleJobDaoImpl();
//        List<ScheduleJob> jobs = dd.getAll();
//        for(ScheduleJob sj : jobs) {
//            System.out.println(sj.domain2String());
//
//        }
        Long jobId = Long.parseLong("e898ec01", 16);
        System.out.println(jobId);
    }
}
