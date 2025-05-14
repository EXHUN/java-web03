package com.itheima.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private String name; //学生姓名
    private Integer degree; //学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士)
    private Integer clazzId; //班级id
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
}
