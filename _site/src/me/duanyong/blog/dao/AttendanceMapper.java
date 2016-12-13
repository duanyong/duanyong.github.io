package me.duanyong.blog.dao;

import me.duanyong.blog.bean.Attendance;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface AttendanceMapper {
    @Delete({
        "delete from attendance",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, type_id, user_id, `date`, first_clock, first_reclock, last_clock, last_reclock, ",
        "late_time, over_time, work_mins, laterin_hours, earlyout_hours, locked, `time`, ",
        "`desc`, `status`",
        "from attendance",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="first_clock", property="firstClock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="first_reclock", property="firstReclock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_clock", property="lastClock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_reclock", property="lastReclock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="late_time", property="lateTime", jdbcType=JdbcType.TIME),
        @Result(column="over_time", property="overTime", jdbcType=JdbcType.TIME),
        @Result(column="work_mins", property="workMins", jdbcType=JdbcType.INTEGER),
        @Result(column="laterin_hours", property="laterinHours", jdbcType=JdbcType.INTEGER),
        @Result(column="earlyout_hours", property="earlyoutHours", jdbcType=JdbcType.INTEGER),
        @Result(column="locked", property="locked", jdbcType=JdbcType.BIT),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    Attendance selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, type_id, user_id, `date`, first_clock, first_reclock, last_clock, last_reclock, ",
        "late_time, over_time, work_mins, laterin_hours, earlyout_hours, locked, `time`, ",
        "`desc`, `status`",
        "from attendance"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="first_clock", property="firstClock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="first_reclock", property="firstReclock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_clock", property="lastClock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_reclock", property="lastReclock", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="late_time", property="lateTime", jdbcType=JdbcType.TIME),
        @Result(column="over_time", property="overTime", jdbcType=JdbcType.TIME),
        @Result(column="work_mins", property="workMins", jdbcType=JdbcType.INTEGER),
        @Result(column="laterin_hours", property="laterinHours", jdbcType=JdbcType.INTEGER),
        @Result(column="earlyout_hours", property="earlyoutHours", jdbcType=JdbcType.INTEGER),
        @Result(column="locked", property="locked", jdbcType=JdbcType.BIT),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<Attendance> selectAll();

    @Update({
        "update attendance",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "`date` = #{date,jdbcType=DATE},",
          "first_clock = #{firstClock,jdbcType=TIMESTAMP},",
          "first_reclock = #{firstReclock,jdbcType=TIMESTAMP},",
          "last_clock = #{lastClock,jdbcType=TIMESTAMP},",
          "last_reclock = #{lastReclock,jdbcType=TIMESTAMP},",
          "late_time = #{lateTime,jdbcType=TIME},",
          "over_time = #{overTime,jdbcType=TIME},",
          "work_mins = #{workMins,jdbcType=INTEGER},",
          "laterin_hours = #{laterinHours,jdbcType=INTEGER},",
          "earlyout_hours = #{earlyoutHours,jdbcType=INTEGER},",
          "locked = #{locked,jdbcType=BIT},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Attendance record);

    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    //去除了time字段
    @Insert({
            "insert into attendance (type_id, user_id, ",
            "`date`, first_clock, ",
            "first_reclock, last_clock, ",
            "last_reclock, late_time, ",
            "over_time, work_mins, ",
            "laterin_hours, earlyout_hours, ",
            "locked)",
            "values (#{typeId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
            "#{date,jdbcType=DATE}, #{firstClock,jdbcType=TIMESTAMP}, ",
            "#{firstReclock,jdbcType=TIMESTAMP}, #{lastClock,jdbcType=TIMESTAMP}, ",
            "#{lastReclock,jdbcType=TIMESTAMP}, #{lateTime,jdbcType=TIME}, ",
            "#{overTime,jdbcType=TIME}, #{workMins,jdbcType=INTEGER}, ",
            "#{laterinHours,jdbcType=INTEGER}, #{earlyoutHours,jdbcType=INTEGER}, ",
            "#{locked,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Attendance record);


    //返回某个用户某天的考勤记录
    @Select({
            "select * from attendance",
            "where `user_id`=#{userId,jdbcType=INTEGER} and `date`=#{date,jdbcType=DATE}",
            "order by `first_clock` asc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="first_clock", property="firstClock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="first_reclock", property="firstReclock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_clock", property="lastClock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_reclock", property="lastReclock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="late_time", property="lateTime", jdbcType=JdbcType.TIME),
            @Result(column="over_time", property="overTime", jdbcType=JdbcType.TIME),
            @Result(column="work_mins", property="workMins", jdbcType=JdbcType.INTEGER),
            @Result(column="laterin_hours", property="laterinHours", jdbcType=JdbcType.INTEGER),
            @Result(column="earlyout_hours", property="earlyoutHours", jdbcType=JdbcType.INTEGER),
            @Result(column="locked", property="locked", jdbcType=JdbcType.BIT),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    Attendance selectByUserAndDate(@Param("userId")Integer userId, @Param("date")Date date);


    @SelectProvider(type = AttendanceSqlProvider.class, method = "getListSqlByRange")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="first_reclock", property="firstReclock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="first_clock", property="firstClock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_reclock", property="lastReclock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_clock", property="lastClock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="late_mins", property="lateMins", jdbcType=JdbcType.INTEGER),
            @Result(column="work_mins", property="workMins", jdbcType=JdbcType.INTEGER),
            @Result(column="over_mins", property="overMins", jdbcType=JdbcType.INTEGER),
            @Result(column="laterin_hours", property="laterinHours", jdbcType=JdbcType.INTEGER),
            @Result(column="earlyout_hours", property="earlyoutHours", jdbcType=JdbcType.INTEGER),
            @Result(column="locked", property="locked", jdbcType=JdbcType.BIT),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<Attendance> selectListInRange(Date start, Date stop, Integer userId);
//    List<Attendance> selectListInRange(@Param("start")Date start, @Param("stop")Date stop, @Param("userId")Integer userId);


    @SelectProvider(type = AttendanceSqlProvider.class, method = "getBadListSqlByRange")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="first_clock", property="firstClock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="first_reclock", property="firstReclock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_clock", property="lastClock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_reclock", property="lastReclock", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="late_time", property="lateTime", jdbcType=JdbcType.TIME),
            @Result(column="over_time", property="overTime", jdbcType=JdbcType.TIME),
            @Result(column="work_mins", property="workMins", jdbcType=JdbcType.INTEGER),
            @Result(column="laterin_hours", property="laterinHours", jdbcType=JdbcType.INTEGER),
            @Result(column="earlyout_hours", property="earlyoutHours", jdbcType=JdbcType.INTEGER),
            @Result(column="locked", property="locked", jdbcType=JdbcType.BIT),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<Attendance> selectBadListInRange(Date startWorkTime, Date stopWorkTime, Date start, Date stop, Integer userId);


    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////
}