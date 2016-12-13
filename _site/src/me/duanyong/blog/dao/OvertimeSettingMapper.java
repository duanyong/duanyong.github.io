package me.duanyong.blog.dao;

import me.duanyong.blog.bean.OvertimeSetting;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import me.duanyong.blog.bean.TimeLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface OvertimeSettingMapper {
    @Delete({
        "delete from overtime_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Select({
        "select",
        "id, user_id, `after`, defer, `desc`, `time`, `status`",
        "from overtime_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="after", property="after", jdbcType=JdbcType.TIME),
        @Result(column="defer", property="defer", jdbcType=JdbcType.REAL),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    OvertimeSetting selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, user_id, `after`, defer, `desc`, `time`, `status`",
        "from overtime_setting",
        "order by `after`"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="after", property="after", jdbcType=JdbcType.TIME),
        @Result(column="defer", property="defer", jdbcType=JdbcType.REAL),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<OvertimeSetting> selectAll();

    @Update({
        "update overtime_setting",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "`after` = #{after,jdbcType=TIME},",
          "defer = #{defer,jdbcType=REAL},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OvertimeSetting record);



    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义函数
    //


    @Insert({
            "insert into overtime_setting (user_id, `after`, defer, `desc)",
            "values (#{userId,jdbcType=INTEGER}, #{after,jdbcType=TIME}, #{defer,jdbcType=REAL}, #{desc,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(OvertimeSetting record);


    @Select({
            "select * from overtime_setting",
            "where `status`=0",
            "order by `after` desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="after", property="after", jdbcType=JdbcType.TIME),
            @Result(column="defer", property="defer", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<OvertimeSetting> getOvertimes();

    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////
}