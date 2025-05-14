package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学员条件分页查询
     */
    public PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        // 1.设置分页参数
        PageHelper.startPage(page,pageSize);
        // 2.执行查询
        List<Student> studentlist = studentMapper.list(name,degree,clazzId);
        // 3.解析查询结果并封装
        Page<Student> p = (Page<Student>) studentlist;
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 添加学员
     */
    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }

    /**
     * 根据ID查询学员
     */
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    /**
     * 修改学员
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 批量删除学员
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /**
     * 违纪处理
     */
    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.violationHandle(id, score);
    }


}
