package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 环绕通知，拦截com.itheima.controller包下的所有增、删、改方法
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Around("@annotation(com.itheima.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        String methodParams = null;
        String returnValue = null;
        try {
            // 获取方法参数
            methodParams = objectMapper.writeValueAsString(joinPoint.getArgs());
            // 执行目标方法
            result = joinPoint.proceed();
            // 获取方法返回值
            returnValue = objectMapper.writeValueAsString(result);
        } catch (Throwable throwable) {
            log.error("Method execution failed", throwable);
            throw throwable;
        } finally {
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 创建操作日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(getCurrentUserId()); // 获取当前操作人ID的方法
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(joinPoint.getTarget().getClass().getName());
            operateLog.setMethodName(joinPoint.getSignature().getName());
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(returnValue);
            operateLog.setCostTime(costTime);

            // 保存操作日志到数据库
            log.info("记录操作日志：{}", operateLog);

            operateLogMapper.insert(operateLog);
        }
        return result;
    }

    /**
     * 获取当前操作人ID的方法
     * @return 当前操作人ID
     */
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}