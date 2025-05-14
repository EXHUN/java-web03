package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
// @Slf4j: lombok提供的注解，用于生成日志对象

// @RequestMapping: 公共请求路径
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class); //定义日志对象

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //method: 指定请求方式
    @GetMapping //等价于上面的注解，不需要指定请求方式
    public Result List() {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
       List<Dept> deptList = deptService.findAll();
       return Result.success(deptList);
    }

    /**
     * 删除部门 方式一：HttpServletRequest request 获取请求参数
     */
    //    @DeleteMapping("/depts")
    //    public Result delete(HttpServletRequest request) {
    //        String idStr = request.getParameter("id");
    //        Integer id = Integer.parseInt(idStr);
    //        System.out.println("根据ID删除部门：" + id);
    //        return Result.success();
    //    }

    /**
     * 删除部门 方式二：@RequestParm
     * 注意事项：一旦声明了@RequestParam，请求参数必须传递，否则会报错(默认required=true)
     */
    //    @DeleteMapping("/depts")
    //    public Result delete(@RequestParam(value = "id",required = false) Integer deptId) {
    //        System.out.println("根据ID删除部门：" + deptId);
    //        return Result.success();
    //    }

    /**
     * 删除部门 方式三：省略@RequestParam注解(前端传递的参数名和服务端方法形参名一致)
     * (推荐)
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
    //System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id); //使用占位符,一个{}对应一个参数，避免了字符串拼接
        if(deptService.hasEmployees(id)){
            return Result.error("该部门下有员工，不能删除");
        }
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     *  新增部门
     */
    // @RequestBody: 接收json格式的请求参数
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
    //System.out.println("新增部门：" + dept);
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     *  根据ID查询部门(查询回显)
     */
    // @PathVariable: 获取路径中的参数
    //    @GetMapping("/depts/{id}")
    //    public Result getInfo(@PathVariable("id") Integer deptId) {
    //        System.out.println("根据ID查询部门：" + deptId);
    //        return Result.success();
    //    }

    /**
     *  根据ID查询部门(查询回显) -- 省略注解内的参数名(前端传递的参数名和服务端方法形参名一致)
     */
    // @PathVariable: 获取路径中的参数
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
    //System.out.println("根据ID查询部门：" + id);
        log.info("根据ID查询部门：{}", id);
       Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     *  修改部门
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
    // System.out.println("修改部门：" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
