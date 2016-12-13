package me.duanyong.blog.dao;

import me.duanyong.blog.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (username, `password`, ",
        "firstname, lastname, ",
        "pinyin, staffno, ",
        "type_id, mobile, ",
        "sex_id, school_id, ",
        "major_id, qq, weixin, ",
        "weibo, salt, login_salt, ",
        "login_intime, login_outime, ",
        "`desc`, `time`, `status`)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=CHAR}, ",
        "#{firstname,jdbcType=VARCHAR}, #{lastname,jdbcType=VARCHAR}, ",
        "#{pinyin,jdbcType=VARCHAR}, #{staffno,jdbcType=VARCHAR}, ",
        "#{typeId,jdbcType=INTEGER}, #{mobile,jdbcType=VARCHAR}, ",
        "#{sexId,jdbcType=INTEGER}, #{schoolId,jdbcType=INTEGER}, ",
        "#{majorId,jdbcType=INTEGER}, #{qq,jdbcType=VARCHAR}, #{weixin,jdbcType=VARCHAR}, ",
        "#{weibo,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, #{loginSalt,jdbcType=CHAR}, ",
        "#{loginIntime,jdbcType=TIMESTAMP}, #{loginOutime,jdbcType=TIMESTAMP}, ",
        "#{desc,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, #{status,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(User record);

    @Select({
        "select",
        "id, username, `password`, firstname, lastname, pinyin, staffno, type_id, mobile, ",
        "sex_id, school_id, major_id, qq, weixin, weibo, salt, login_salt, login_intime, ",
        "login_outime, `desc`, `time`, `status`",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="firstname", property="firstname", jdbcType=JdbcType.VARCHAR),
        @Result(column="lastname", property="lastname", jdbcType=JdbcType.VARCHAR),
        @Result(column="pinyin", property="pinyin", jdbcType=JdbcType.VARCHAR),
        @Result(column="staffno", property="staffno", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex_id", property="sexId", jdbcType=JdbcType.INTEGER),
        @Result(column="school_id", property="schoolId", jdbcType=JdbcType.INTEGER),
        @Result(column="major_id", property="majorId", jdbcType=JdbcType.INTEGER),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="weibo", property="weibo", jdbcType=JdbcType.VARCHAR),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_salt", property="loginSalt", jdbcType=JdbcType.CHAR),
        @Result(column="login_intime", property="loginIntime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="login_outime", property="loginOutime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    User selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, username, `password`, firstname, lastname, pinyin, staffno, type_id, mobile, ",
        "sex_id, school_id, major_id, qq, weixin, weibo, salt, login_salt, login_intime, ",
        "login_outime, `desc`, `time`, `status`",
        "from user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="firstname", property="firstname", jdbcType=JdbcType.VARCHAR),
        @Result(column="lastname", property="lastname", jdbcType=JdbcType.VARCHAR),
        @Result(column="pinyin", property="pinyin", jdbcType=JdbcType.VARCHAR),
        @Result(column="staffno", property="staffno", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex_id", property="sexId", jdbcType=JdbcType.INTEGER),
        @Result(column="school_id", property="schoolId", jdbcType=JdbcType.INTEGER),
        @Result(column="major_id", property="majorId", jdbcType=JdbcType.INTEGER),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="weibo", property="weibo", jdbcType=JdbcType.VARCHAR),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_salt", property="loginSalt", jdbcType=JdbcType.CHAR),
        @Result(column="login_intime", property="loginIntime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="login_outime", property="loginOutime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=CHAR},",
          "firstname = #{firstname,jdbcType=VARCHAR},",
          "lastname = #{lastname,jdbcType=VARCHAR},",
          "pinyin = #{pinyin,jdbcType=VARCHAR},",
          "staffno = #{staffno,jdbcType=VARCHAR},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "sex_id = #{sexId,jdbcType=INTEGER},",
          "school_id = #{schoolId,jdbcType=INTEGER},",
          "major_id = #{majorId,jdbcType=INTEGER},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "weixin = #{weixin,jdbcType=VARCHAR},",
          "weibo = #{weibo,jdbcType=VARCHAR},",
          "salt = #{salt,jdbcType=VARCHAR},",
          "login_salt = #{loginSalt,jdbcType=CHAR},",
          "login_intime = #{loginIntime,jdbcType=TIMESTAMP},",
          "login_outime = #{loginOutime,jdbcType=TIMESTAMP},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);


    @Select({
            "select",
            "id, username, `password`, firstname, lastname, pinyin, staffno, type_id, mobile, ",
            "sex_id, school_id, major_id, qq, weixin, weibo, salt, login_salt, login_intime, ",
            "login_outime, `desc`, `time`, `status`",
            "from user",
            "where staffno = #{staffno,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
            @Result(column="firstname", property="firstname", jdbcType=JdbcType.VARCHAR),
            @Result(column="lastname", property="lastname", jdbcType=JdbcType.VARCHAR),
            @Result(column="pinyin", property="pinyin", jdbcType=JdbcType.VARCHAR),
            @Result(column="staffno", property="staffno", jdbcType=JdbcType.VARCHAR),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="sex_id", property="sexId", jdbcType=JdbcType.INTEGER),
            @Result(column="school_id", property="schoolId", jdbcType=JdbcType.INTEGER),
            @Result(column="major_id", property="majorId", jdbcType=JdbcType.INTEGER),
            @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
            @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
            @Result(column="weibo", property="weibo", jdbcType=JdbcType.VARCHAR),
            @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_salt", property="loginSalt", jdbcType=JdbcType.CHAR),
            @Result(column="login_intime", property="loginIntime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="login_outime", property="loginOutime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    User selectByStaffno(String staffno);
}