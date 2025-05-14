package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    /**
     * 分页查询
     */

    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * 添加班级
     */
    void addClazz(Clazz clazz);
    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void alterClazz(Clazz clazz);

    /**
     * 删除班级
     */

    void deleteById(Integer id);

    /**
     * 查询所有班级
     */
    List<Clazz> list();
}
