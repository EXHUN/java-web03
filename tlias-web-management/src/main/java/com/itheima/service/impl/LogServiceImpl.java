package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.LogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

        /**
         *  日志分页查询
         */
        public PageResult<OperateLog> page(Integer operateEmpId, LocalDateTime operateTime,String className,String methodName,Long costTime,String methodParams,String returnValue,Integer page,Integer pageSize) {
            //1.设置分页参数
            PageHelper.startPage(page, pageSize);
            //2.执行查询
            List<OperateLog> loglist = logMapper.list(operateEmpId,operateTime,className,methodName,costTime,methodParams,returnValue);
            //3.解析查询结果并封装
            Page<OperateLog> p = (Page<OperateLog>) loglist;
            return new PageResult(p.getTotal(), p.getResult());
        }

}

