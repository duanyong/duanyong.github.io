package me.duanyong.blog.dao;

import me.duanyong.blog.bean.Vacation;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface VacationMapper {
    @Delete({
        "delete from vacation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into vacation (type_id, user_id, ",
        "`date`, `hours`, `desc`, ",
        "`time`, `status`)",
        "values (#{typeId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{date,jdbcType=DATE}, #{hours,jdbcType=REAL}, #{desc,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Vacation record);

    @Select({
        "select",
        "id, type_id, user_id, `date`, `hours`, `desc`, `time`, `status`",
        "from vacation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    Vacation selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, type_id, user_id, `date`, `hours`, `desc`, `time`, `status`",
        "from vacation"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Vacation> selectAll();

    @Update({
        "update vacation",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "`date` = #{date,jdbcType=DATE},",
          "`hours` = #{hours,jdbcType=REAL},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Vacation record);


    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    @Select({
            "select * from vacation where",
            "`user_id`=#{userId,jdbcType=INTEGER}",
            "and `date`=#{date,jdbcType=DATE}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Vacation> selectByUserAndDate(@Param("userId")Integer userId, @Param("date")Date date);


    @Select({
            "select * from vacation where",
            "`user_id`=#{userId,jdbcType=INTEGER}",
            "and `type_id`=#{typeId,jdbcType=INTEGER}",
            "and `date`=#{date,jdbcType=DATE}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="date", property="date", jdbcType=JdbcType.DATE),
            @Result(column="hours", property="hours", jdbcType=JdbcType.REAL),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Vacation> selectByUserAndDateAndType(@Param("userId")Integer userId, @Param("date")Date date, @Param("typeId")Integer typeId);


    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////
}