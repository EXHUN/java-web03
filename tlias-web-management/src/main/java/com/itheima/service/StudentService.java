package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 学员列表分页查询
     */
    PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    /**
     * 添加学员
     */
    void add(Student student);

    /**
     * 根据ID查询学员
     */
    Student getInfo(Integer id);

    /**
     * 修改学员
     */
    void update(Student student);

    /**
     * 批量删除学员
     */
    void delete(List<Integer> ids);

    /**
     * 违纪处理
     */
    void violationHandle(Integer id, Integer score);
}
