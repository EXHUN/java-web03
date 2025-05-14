package com.itheima.controller;


import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class LogController {
    @Autowired
    private LogService logService;

    /**
     *  日志列表查询
     */
    @GetMapping("/log/page")
    public Result page(Integer operateEmpId,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime operateTime,
                       String className,
                       String methodName,
                       Long costTime,
                       String methodParams,
                       String returnValue,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<OperateLog> pageResult = logService.page(operateEmpId, operateTime, className, methodName, costTime, methodParams, returnValue, page, pageSize);

        return Result.success(pageResult);
    }
}
