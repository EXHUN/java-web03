package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(String name, Integer degree, Integer clazzId);

    /**
     * 添加学员
     */
    @Insert("insert into student(name, no, gender, phone,id_card, is_college, address, degree, graduation_date,clazz_id, create_time, update_time) values(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void add(Student student);

    /**
     * 查询班级下是否有学员(删除班级)
     */
    @Select("select count(*) from student where clazz_id = #{id}")
    Student getById(Integer id);

    /**
     * 根据ID查询学员
     */
    @Select("select * from student where id = #{id}")
    Student getInfo(Integer id);

    /**
     * 修改学员
     */
    void update(Student student);

    /**
     * 批量删除学员
     */
    void delete(List<Integer> ids);

    /**
     * 批量插入
     */
    void insertBatch(List<Student> studentList);

    /**
     * 违纪处理
     */
    @Update("UPDATE student SET violation_count = violation_count + 1, violation_score = violation_score + #{score} WHERE id = #{id}")
    void violationHandle(Integer id, Integer score);

    /**
     * 统计班级人数
     */
    @Select("select c.name cname , count(s.id) scount from clazz c  left join student s on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    List<Map<String,Object>> getStudentCount();

    /**
     * 统计学员学历
     */
    @MapKey("name")
    List<Map> countStudentDegreeData();
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);
}
