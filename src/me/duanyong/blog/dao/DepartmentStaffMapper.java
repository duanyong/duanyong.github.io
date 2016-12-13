package me.duanyong.blog.dao;

import me.duanyong.blog.bean.DepartmentStaff;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface DepartmentStaffMapper {
    @Delete({
        "delete from department_staff",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into department_staff (department_id, title_id, user_id, `desc`, `time`,`status`)",
        "values (",
        "#{departmentId,jdbcType=INTEGER},",
        "#{titleId,jdbcType=INTEGER},",
        "#{userId,jdbcType=INTEGER}, #{desc,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(DepartmentStaff record);

    @Select({
        "select",
        "id, department_id, title_id, user_id, `desc`, `time`, `status`",
        "from department_staff",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="title_id", property="titleId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    DepartmentStaff selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, department_id, title_id, user_id, `desc`, `time`, `status`",
        "from department_staff"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="title_id", property="titleId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<DepartmentStaff> selectAll();

    @Update({
        "update department_staff",
        "set department_id = #{departmentId,jdbcType=INTEGER},",
          "title_id = #{titleId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DepartmentStaff record);

    @Select({
            "select",
            "id, department_id, title_id, user_id, `desc`, `time`, `status`",
            "from department_staff",
            "where department_id = #{departmentId,jdbcType=INTEGER} and user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="title_id", property="titleId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    DepartmentStaff selectByDepartmentAndUser(@Param("departmentId")Integer departmentId, @Param("userId")Integer userId);

    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    @Select({
            "select * from department_staff",
            "where `department_id`=#{departId,jdbcType=INTEGER} and `status`=0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="title_id", property="titleId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<DepartmentStaff> listStaffByDepartmentId(@Param("departId")Integer departId);

    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////
}