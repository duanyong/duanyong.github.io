package me.duanyong.blog.dao;


import org.apache.ibatis.jdbc.SQL;
import me.duanyong.handswork.util.DateTimeUtil;

import java.util.Date;

public class AttendanceSqlProvider {

    public String getListSqlByRange(Date date1, Date date2, Integer userId) {
        return new SQL() {{
            SELECT("*");
            FROM("attendance");

            if (userId != null) {
                WHERE("user_id=" + userId);
//                WHERE("user_id=#{userId,jdbcType=INTEGER}");
            }

            if (date1 != null) {
                WHERE("`date`>='" + date1.toString() + "'");
//                WHERE("`date`>=#{date1,jdbcType=DATE}");
            }

            if (date2 != null) {
                WHERE("`date`<='" + date2.toString() + "'");
//                WHERE("`date`<=#{date2,jdbcType=DATE}");
            }

            WHERE("`status`=0");

        }}.toString();
    }

    public String getBadListSqlByRange(Date startWorkTime, Date stopWorkTime, Date date1, Date date2, Integer userId) {
        return new SQL() {{
            SELECT("*");
            FROM("attendance");
            WHERE("`status`=0");

            if (userId != null) {
                WHERE("user_id=#{userId,jdbcType=INTEGER}");
            }

            if (date1 != null) {
                WHERE("`date`>=#{date1,jdbcType=DATE}");
            }

            if (date2 != null) {
                WHERE("`date`<=#{date2,jdbcType=DATE}");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("first_clock=null");
            sb.append(" or last_clock=null");
            sb.append(" or HOUR(`first_clock`)>9");
            sb.append(" or HOUR(`last_clock`)<18");

            AND();
            WHERE(sb.toString());
        }}.toString();
    }

}