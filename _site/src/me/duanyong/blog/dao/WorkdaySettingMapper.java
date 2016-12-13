package me.duanyong.blog.dao;

import me.duanyong.blog.bean.WorkdaySetting;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WorkdaySettingMapper {
    @Delete({
        "delete from workday_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into workday_setting (user_id, type_id, ",
        "`name`, start_time, stop_time, ",
        "`hours`, `scale`, `desc`, ",
        "`time`, `status`)",
        "values (#{userId,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{startTime,jdbcType=TIME}, #{stopTime,jdbcType=TIME}, ",
        "#{hours,jdbcType=REAL}, #{scale,jdbcType=REAL}, #{desc,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP}, #{status,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(WorkdaySetting record);

    @Select({
        "select",
        "id, user_id, type_id, `name`, start_time, stop_time, `hours`, `scale`, `desc`, ",
        "`time`, `status`",
        "from workday_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIME),
        @Result(column="stop_time", property="stopTime", jdbcType=JdbcType.TIME),
        @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
        @Result(column="scale", property="scale", jdbcType=JdbcType.REAL),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    WorkdaySetting selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, user_id, type_id, `name`, start_time, stop_time, `hours`, `scale`, `desc`, ",
        "`time`, `status`",
        "from workday_setting"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIME),
        @Result(column="stop_time", property="stopTime", jdbcType=JdbcType.TIME),
        @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
        @Result(column="scale", property="scale", jdbcType=JdbcType.REAL),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<WorkdaySetting> selectAll();

    @Update({
        "update workday_setting",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "`name` = #{name,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=TIME},",
          "stop_time = #{stopTime,jdbcType=TIME},",
          "`hours` = #{hours,jdbcType=REAL},",
          "`scale` = #{scale,jdbcType=REAL},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WorkdaySetting record);

    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    @Select({
            "select * from workday_setting",
            "where `type_id`=#{typeId,jdbcType=INTEGER} and `status`=0",
            "order by `start_time` asc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIME),
            @Result(column="stop_time", property="stopTime", jdbcType=JdbcType.TIME),
            @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
            @Result(column="scale", property="scale", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<WorkdaySetting> selectByType(@Param("typeId")Integer typeId);

    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////

}