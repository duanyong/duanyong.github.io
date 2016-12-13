package me.duanyong.blog.dao;

import me.duanyong.blog.bean.Department;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface DepartmentMapper {
    @Delete({
        "delete from department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into department (`name`, type_id, ",
        "leader_id, deputy_id, ",
        "assist_id, `count`, ",
        "`desc`, `time`, `status`)",
        "values (#{name,jdbcType=VARCHAR}, #{typeId,jdbcType=INTEGER}, ",
        "#{leaderId,jdbcType=INTEGER}, #{deputyId,jdbcType=INTEGER}, ",
        "#{assistId,jdbcType=INTEGER}, #{count,jdbcType=SMALLINT}, ",
        "#{desc,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, #{status,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Department record);

    @Select({
        "select",
        "id, `name`, type_id, leader_id, deputy_id, assist_id, `count`, `desc`, `time`, ",
        "`status`",
        "from department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="leader_id", property="leaderId", jdbcType=JdbcType.INTEGER),
        @Result(column="deputy_id", property="deputyId", jdbcType=JdbcType.INTEGER),
        @Result(column="assist_id", property="assistId", jdbcType=JdbcType.INTEGER),
        @Result(column="count", property="count", jdbcType=JdbcType.SMALLINT),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    Department selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, `name`, type_id, leader_id, deputy_id, assist_id, `count`, `desc`, `time`, ",
        "`status`",
        "from department"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="leader_id", property="leaderId", jdbcType=JdbcType.INTEGER),
        @Result(column="deputy_id", property="deputyId", jdbcType=JdbcType.INTEGER),
        @Result(column="assist_id", property="assistId", jdbcType=JdbcType.INTEGER),
        @Result(column="count", property="count", jdbcType=JdbcType.SMALLINT),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<Department> selectAll();

    @Update({
        "update department",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "leader_id = #{leaderId,jdbcType=INTEGER},",
          "deputy_id = #{deputyId,jdbcType=INTEGER},",
          "assist_id = #{assistId,jdbcType=INTEGER},",
          "`count` = #{count,jdbcType=SMALLINT},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Department record);

    @Select({
            "select",
            "id, `name`, type_id, leader_id, deputy_id, assist_id, `count`, `desc`, `time`, ",
            "`status`",
            "from department",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="leader_id", property="leaderId", jdbcType=JdbcType.INTEGER),
            @Result(column="deputy_id", property="deputyId", jdbcType=JdbcType.INTEGER),
            @Result(column="assist_id", property="assistId", jdbcType=JdbcType.INTEGER),
            @Result(column="count", property="count", jdbcType=JdbcType.SMALLINT),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    Department selectByName(String name);
}