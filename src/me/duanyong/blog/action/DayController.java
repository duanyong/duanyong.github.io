package me.duanyong.blog.action;

import me.duanyong.blog.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;


/**
 * Created by duanyong on 9/14/16.
 */

@RestController
@RequestMapping("/day")
public class DayController extends BaseRestAction {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private WebApplicationContext context;

    //将打卡机上的考勤日志，同步到考勤系统中
    @RequestMapping(path = "/init", method = RequestMethod.GET)
    public String upload(@RequestParam(value = "year", required = false)Integer year) {
        if (year == null
                || year < 1997) {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }

        context.getBean(DaySettingService.class).initByYear(year);

        return gson();
    }
}
