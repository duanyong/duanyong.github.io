package me.duanyong.blog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import me.duanyong.handswork.util.DateTimeUtil;

import java.util.Date;

/**
 * Created by duanyong on 9/12/16.
 */
public class Test {

    private static final Logger log = LogManager.getLogger();

    /**
     * Created by duanyong on 9/12/16.
     */
    public static void main(String[] args) throws Throwable {
        Date date1 = DateTimeUtil.getDateByString("2016-10-10 26:10:10", DateTimeUtil.FULLFORTMATER);
        Date date2 = DateTimeUtil.getDateByString("2016-10-11 02:10:10", DateTimeUtil.FULLFORTMATER);

        log.info(date1.toString());
        log.info(date1.after(date2));
        log.info(date1.before(date2));

        log.info(date1.compareTo(date2));


    }


}
