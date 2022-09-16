package com.atguigu.base.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    /**
     * 保存一个T
     */
    Integer insert(T t);


    /**
     * 删除
     * @param id 标识ID 可以是自增长ID，也可以是唯一标识。
     */
    Integer delete(Serializable id);

    /**
     * 更新一个实体
     */
    Integer update(T t);

    /**
     * 通过一个标识ID 获取一个唯一实体
     *
     * @param id 标识ID 可以是自增长ID，也可以是唯一标识。
     */
    T getById(Serializable id);

    List<T> findPage(Map<String, Object> filters);
}