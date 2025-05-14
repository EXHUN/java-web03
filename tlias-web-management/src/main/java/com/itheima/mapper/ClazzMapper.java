package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 条件查询员工信息
     */

     List<Clazz> list(String name, LocalDate begin, LocalDate end);
    /**
     * 添加班级
     */
    @Insert("insert into clazz VALUES (null,#{name},#{room},#{beginDate},#{endDate},#{masterId}, #{subject},#{createTime},#{updateTime})")
    void addClazz(Clazz clazz);

    /**
     * 根据ID查询班级
     */

    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 动态更新班级
     */
//    @Update("update clazz set name = #{name},room = #{room},begin_date = #{beginDate},end_date = #{endDate},master_id = #{masterId},subject = #{subject},update_time = #{updateTime} where id = #{id}")
    void alterClazz(Clazz clazz);

    /**
     * 删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 查询所有班级
     */
    @Select("select * from clazz")
    List<Clazz> listAll();
}
