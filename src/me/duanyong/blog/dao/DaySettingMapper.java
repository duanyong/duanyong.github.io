package me.duanyong.blog.dao;

import me.duanyong.blog.bean.DaySetting;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface DaySettingMapper {
    @Delete({
        "delete from day_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Select({
        "select",
        "id, user_id, type_id, `date`, `alias`, `scale`, `desc`, `time`, `status`",
        "from day_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale", property="scale", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    DaySetting selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, user_id, type_id, `date`, `alias`, `scale`, `desc`, `time`, `status`",
        "from day_setting"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale", property="scale", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<DaySetting> selectAll();

    @Update({
        "update day_setting",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "`date` = #{date,jdbcType=DATE},",
          "`alias` = #{alias,jdbcType=VARCHAR},",
          "`scale` = #{scale,jdbcType=REAL},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DaySetting record);

    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    @Insert({
            "insert into day_setting (user_id, type_id, `date`, `alias`, `scale`, `desc`)",
            "values (",
            "#{userId,jdbcType=INTEGER},",
            "#{typeId,jdbcType=INTEGER},",
            "#{date,jdbcType=DATE},",
            "#{alias,jdbcType=VARCHAR},",
            "#{scale,jdbcType=REAL},",
            "#{desc,jdbcType=VARCHAR}",
            ")"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(DaySetting record);

    @Select({
            "select * from day_setting",
            "where `date`=#{date,jdbcType=DATE} and `status`=0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale", property="scale", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    DaySetting findByDate(@Param("date")Date date);

    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////

}