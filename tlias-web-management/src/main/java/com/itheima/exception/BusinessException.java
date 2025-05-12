package com.itheima.exception;


    // 班级有学生异常(自定义异常处理)
    public class BusinessException extends RuntimeException{
        public BusinessException(String message) {
            super(message);
        }
    }

