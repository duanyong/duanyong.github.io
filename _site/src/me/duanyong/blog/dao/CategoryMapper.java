package me.duanyong.blog.dao;

import me.duanyong.blog.bean.Category;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface CategoryMapper {
    @Delete({
        "delete from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into category (top_id, parent_id, ",
        "`name`, `alias`, css, ",
        "css1, css2, `zone`, ",
        "pos, ctime, `status`)",
        "values (#{topId,jdbcType=INTEGER}, #{parentId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{alias,jdbcType=VARCHAR}, #{css,jdbcType=VARCHAR}, ",
        "#{css1,jdbcType=VARCHAR}, #{css2,jdbcType=VARCHAR}, #{zone,jdbcType=SMALLINT}, ",
        "#{pos,jdbcType=SMALLINT}, #{ctime,jdbcType=TIMESTAMP}, #{status,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Category record);

    @Select({
        "select",
        "id, top_id, parent_id, `name`, `alias`, css, css1, css2, `zone`, pos, ctime, ",
        "`status`",
        "from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="top_id", property="topId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
        @Result(column="css", property="css", jdbcType=JdbcType.VARCHAR),
        @Result(column="css1", property="css1", jdbcType=JdbcType.VARCHAR),
        @Result(column="css2", property="css2", jdbcType=JdbcType.VARCHAR),
        @Result(column="zone", property="zone", jdbcType=JdbcType.SMALLINT),
        @Result(column="pos", property="pos", jdbcType=JdbcType.SMALLINT),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    Category selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, top_id, parent_id, `name`, `alias`, css, css1, css2, `zone`, pos, ctime, ",
        "`status`",
        "from category"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="top_id", property="topId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
        @Result(column="css", property="css", jdbcType=JdbcType.VARCHAR),
        @Result(column="css1", property="css1", jdbcType=JdbcType.VARCHAR),
        @Result(column="css2", property="css2", jdbcType=JdbcType.VARCHAR),
        @Result(column="zone", property="zone", jdbcType=JdbcType.SMALLINT),
        @Result(column="pos", property="pos", jdbcType=JdbcType.SMALLINT),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<Category> selectAll();

    @Update({
        "update category",
        "set top_id = #{topId,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "`name` = #{name,jdbcType=VARCHAR},",
          "`alias` = #{alias,jdbcType=VARCHAR},",
          "css = #{css,jdbcType=VARCHAR},",
          "css1 = #{css1,jdbcType=VARCHAR},",
          "css2 = #{css2,jdbcType=VARCHAR},",
          "`zone` = #{zone,jdbcType=SMALLINT},",
          "pos = #{pos,jdbcType=SMALLINT},",
          "ctime = #{ctime,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);

    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    @Select({
            "select * from category",
            "where `name`=#{name,jdbcType=VARCHAR} and `top_id`=#{topId,jdbcType=INTEGER}"
    })
    Category findByName(@Param("name")String name, @Param("topId")Integer topId);

    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////
}