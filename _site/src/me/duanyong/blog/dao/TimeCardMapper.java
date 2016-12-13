package me.duanyong.blog.dao;

import me.duanyong.blog.bean.TimeCard;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface TimeCardMapper {
    @Delete({
        "delete from time_card",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into time_card (type_id, user_id, ",
        "cardno, `desc`, `time`, ",
        "`status`)",
        "values (#{typeId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{cardno,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TimeCard record);

    @Select({
        "select",
        "id, type_id, user_id, cardno, `desc`, `time`, `status`",
        "from time_card",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="cardno", property="cardno", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeCard selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, type_id, user_id, cardno, `desc`, `time`, `status`",
        "from time_card"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="cardno", property="cardno", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<TimeCard> selectAll();

    @Update({
        "update time_card",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "cardno = #{cardno,jdbcType=VARCHAR},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TimeCard record);

    @Select({
            "select",
            "id, type_id, user_id, cardno, `desc`, `time`, `status`",
            "from time_card",
            "where ",
            "user_id = #{userId,jdbcType=INTEGER}",
            " and ",
            "cardno = #{cardNo,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="cardno", property="cardno", jdbcType=JdbcType.VARCHAR),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeCard selectByUserAndCard(@Param("userId")Integer userId, @Param("cardNo")String cardNo);

}