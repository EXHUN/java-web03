package com.itheima.mapper;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface LogMapper {
    /**
     * 日志列表查询
     */
    List<OperateLog> list(Integer operateEmpId, LocalDateTime operateTime, String className, String methodName, Long costTime, String methodParams, String returnValue);
}
