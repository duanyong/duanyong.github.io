package me.duanyong.blog.dao;

import me.duanyong.blog.bean.TimeLog;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface TimeLogMapper {


    @Delete({
        "delete from time_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into time_log (time_device_id, time_card_id, ",
        "user_id, `date`, `time`, ",
        "`desc`, `status`)",
        "values (#{timeDeviceId,jdbcType=INTEGER}, #{timeCardId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, #{date,jdbcType=DATE}, #{time,jdbcType=TIME}, ",
        "#{desc,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT})"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="time_device_id", property="timeDeviceId", jdbcType=JdbcType.INTEGER),
            @Result(column="time_card_id", property="timeCardId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIME),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TimeLog record);

    @Select({
        "select",
        "id, time_device_id, time_card_id, user_id, `date`, `time`, `desc`, `status`",
        "from time_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="time_device_id", property="timeDeviceId", jdbcType=JdbcType.INTEGER),
            @Result(column="time_card_id", property="timeCardId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIME),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeLog selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, time_device_id, time_card_id, user_id, `date`, `time`, `desc`, `status`",
        "from time_log"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="time_device_id", property="timeDeviceId", jdbcType=JdbcType.INTEGER),
        @Result(column="time_card_id", property="timeCardId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="time", property="time", jdbcType=JdbcType.TIME),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<TimeLog> selectAll();

    @Update({
        "update time_log",
        "set time_device_id = #{timeDeviceId,jdbcType=INTEGER},",
          "time_card_id = #{timeCardId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "`date` = #{date,jdbcType=DATE},",
          "`time` = #{time,jdbcType=TIME},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TimeLog record);

    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义函数
    //


    @Select({
            "select *",
            "from time_log",
            "where user_id = #{userId,jdbcType=INTEGER} and `date`= #{time,jdbcType=DATE} and `time`= #{time,jdbcType=TIME}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="time_device_id", property="timeDeviceId", jdbcType=JdbcType.INTEGER),
            @Result(column="time_card_id", property="timeCardId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIME),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeLog selectByUserAndTime(@Param("userId")Integer userId, @Param("date")Date date, @Param("time")Date time);

    @Select({
            "select *",
            "from time_log",
            "where user_id = #{userId,jdbcType=INTEGER} and `date`= #{date,jdbcType=DATE} order by `time` asc limit 1"
    })
    TimeLog getFirstTimeByUserAndDate(@Param("userId")Integer userId, @Param("date")Date date);

    @Select({
            "select *",
            "from time_log",
            "where user_id = #{userId,jdbcType=INTEGER} and `date`= #{date,jdbcType=DATE} order by `time` desc limit 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="time_device_id", property="timeDeviceId", jdbcType=JdbcType.INTEGER),
            @Result(column="time_card_id", property="timeCardId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIME),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeLog getLastTimeByUserAndDate(@Param("userId")Integer userId, @Param("date")Date date);


    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////

}