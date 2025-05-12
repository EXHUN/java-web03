package com.itheima.exception;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错啦~",e);
        return Result.error("出错啦，请联系管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错啦~",e);
        String message = e.getMessage(); //获取异常的消息。
        int i = message.indexOf("Duplicate entry"); //找到消息中"Duplicate entry"的起始位置
        String errMsg = message.substring(i); //截取从"Duplicate entry"开始的子字符串。
        String[] arr = errMsg.split(" "); //将子字符串按空格分割成数组。
        return Result.error(arr[2] + "已存在"); //返回一个错误结果，提示用户哪个值已存在。
    }

    @ExceptionHandler
    public Result handleBuinessException(BusinessException businessException) {
        log.error("服务器异常", businessException);
        return Result.error(businessException.getMessage());
    }
}
