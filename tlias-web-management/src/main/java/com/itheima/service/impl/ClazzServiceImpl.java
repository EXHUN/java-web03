package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.service.ClazzService;
import com.itheima.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询
     */
    public PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.执行查询
        List<Clazz> datalist = clazzMapper.list(name,begin,end);
        //3.解析查询结果并封装
        Page<Clazz> p = (Page<Clazz>) datalist;
        return new PageResult(p.getTotal(), p.getResult());
    }


    /**
     * 添加班级
     */
    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }


    /**
     * 根据ID查询班级
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }


    /**
     * 修改班级
     */
    @Override
    public void alterClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.alterClazz(clazz);
    }

    /**
     * 删除班级
     */
    @Override
    public void deleteById(Integer id) {
        //1. 查询班级下是否有学员
        Integer count = studentMapper.countByClazzId(id);
        if(count > 0){
            throw new BusinessException("班级下有学员, 不能直接删除~");
        }
        //2. 如果没有, 再删除班级信息
        clazzMapper.deleteById(id);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> list() {
        return clazzMapper.listAll();
    }

}
