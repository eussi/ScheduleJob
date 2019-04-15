package com.eussi.schedulejob.dao;

import java.util.List;

/**
 * Created by wangxueming on 2018/6/18.
 */
public interface DomainDao<T> {
    int deleteByPrimaryKey(String jobId);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> getAll();
}
