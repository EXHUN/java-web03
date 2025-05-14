package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

//    @Autowired
//    private EmpService empService;



    // 班级条件分页查询
    @GetMapping
    public Result page(String name ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10")Integer pageSize) {
        PageResult pageResult = clazzService.page(name,begin,end,page,pageSize);
        return Result.success(pageResult);
    }



//    //查询所有员工
//    @GetMapping("/list")
//    public Result findAll() {
//        List<Emp> EmpList = empService.findAll();
//        return Result.success(EmpList);
//    }

    //添加班级
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        clazzService.addClazz(clazz);
        return Result.success();
    }

    //根据ID查询班级
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    //修改班级
    @PutMapping
    public Result alterClazz(@RequestBody Clazz clazz) {
        clazzService.alterClazz(clazz);
        return Result.success();
    }

    //删除班级
    @DeleteMapping("/{id}")
    public Result deleteClazz (@PathVariable("id") Integer id) {
        clazzService.deleteById(id);
        return Result.success();
    }

    // 查询所有班级
    @GetMapping("/list")
    public Result list() {
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }
}
