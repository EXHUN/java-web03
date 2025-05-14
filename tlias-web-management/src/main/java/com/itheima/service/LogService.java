package com.itheima.service;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

import java.time.LocalDateTime;

public interface LogService {
    /**
     *  日志列表查询
     */
    PageResult<OperateLog> page(Integer operateEmpId, LocalDateTime operateTime, String className, String methodName, Long costTime, String methodParams, String returnValue,Integer page,Integer pageSize);
}
