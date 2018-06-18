package com.dcits.schedulejob.dao;

import com.dcits.schedulejob.domain.ScheduleJob;

import java.util.List;

/**
 * Created by wangxueming on 2018/6/18.
 */
public interface DomainDao<T> {
    int deleteByPrimaryKey(Long jobId);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> getAll();
}
