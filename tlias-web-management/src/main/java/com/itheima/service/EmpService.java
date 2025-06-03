package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void save(Emp emp) throws Exception;


    /**
     * 批量删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     */
    void update(Emp emp);

    /**
     * 查询所有员工
     */
    List<Emp> findAll();

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);

    /**
     * 修改密码
     * @param id 当前登录员工id
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return true-成功 false-失败
     */
    boolean updatePwd(Integer id, String oldPwd, String newPwd);
}
