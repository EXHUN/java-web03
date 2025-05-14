package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogQueryParam {
    private Integer operateEmpId; //操作人ID
    private LocalDateTime operateTime; //操作时间
    private String className; //操作类名
    private String methodName; //操作方法名
    private Long costTime; //操作耗时
    private String methodParams; //操作方法参数
    private String returnValue; //操作方法返回值
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
}
