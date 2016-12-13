package me.duanyong.blog.dao;

import me.duanyong.blog.bean.TimeDevice;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TimeDeviceMapper {
    @Delete({
        "delete from time_device",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into time_device (sn, ip, ",
        "title, `desc`, `time`, ",
        "`status`)",
        "values (#{sn,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=TINYINT})"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="sn", property="sn", jdbcType=JdbcType.VARCHAR),
            @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })

    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TimeDevice record);

    @Select({
        "select",
        "id, sn, ip, title, `desc`, `time`, `status`",
        "from time_device",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="sn", property="sn", jdbcType=JdbcType.VARCHAR),
            @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeDevice selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, sn, ip, title, `desc`, `time`, `status`",
        "from time_device"
    })
    List<TimeDevice> selectAll();

    @Update({
        "update time_device",
        "set sn = #{sn,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TimeDevice record);

    @Select({
            "select",
            "id, sn, ip, title, `desc`, `time`, `status`",
            "from time_device",
            "where sn = #{sn,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="sn", property="sn", jdbcType=JdbcType.VARCHAR),
            @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    TimeDevice selectBySn(String sn);
}