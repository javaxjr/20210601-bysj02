package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Admin;
import com.tjetc.domain.People;
import org.apache.poi.ss.formula.functions.T;

/*
* 父接口  里面仅定义抽象的方法  不定义具体的实现
* */
public interface PeopleService {
    public T add(T t);

    PageInfo<T> listByName(String name, Integer pageNum, Integer pageSize);
}
