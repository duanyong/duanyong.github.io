package me.duanyong.blog.action;

import me.duanyong.blog.bean.Attendance;
import me.duanyong.blog.bean.TimeLog;
import me.duanyong.blog.bean.User;
import me.duanyong.blog.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import me.duanyong.handswork.util.DateTimeUtil;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by duanyong on 9/14/16.
 */

@RestController
@RequestMapping("/attend")
public class AttendanceController extends BaseRestAction {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private WebApplicationContext context;
//
//    @RequestMapping(path="", method = RequestMethod.GET)
//    public ModelAndView index() {
//        return new ModelAndView("/attend/index");
//    }

    //按页返回考勤记录
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "month", required = false) @DateTimeFormat(pattern="yyyy-MM") Date month,
                             @RequestParam(value = "date1", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                             @RequestParam(value = "date2", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date2,

                             //显示考勤异常
                             @RequestParam(value = "bad", required = false) Boolean bad,

                             @RequestParam(value = "uid", required = false) Integer uid,
                             @RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size
    ) {
        if (page == null) {
            page = 1;
        }

        if (size == null) {
            size = 50;
        }

        if (month != null) {
            //将月份换为当月的起止日期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(month);

            String str1 = new SimpleDateFormat("yyyy-MM-01 00:00:00").format(month);
            String str2  = new SimpleDateFormat(String.format("yyyy-MM-%s 00:00:00", calendar.getActualMaximum(Calendar.DAY_OF_MONTH))).format(month);


            try {
                date1 = new SimpleDateFormat(DateTimeUtil.FULLFORTMATER).parse(str1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                date2 = new SimpleDateFormat(DateTimeUtil.FULLFORTMATER).parse(str2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        List<Attendance> list = Boolean.TRUE.equals(bad)
                ? context.getBean(AttendanceService.class).findOnlyBadByMonth(date1, date2, uid, 1, 100)
                : context.getBean(AttendanceService.class).findByMonth(date1, date2, uid, 1, 100);
        Map<String, User> users = new HashMap<>();


        if (list == null) {
            list = new ArrayList<>();
        }

        UserService userService = context.getBean(UserService.class);
        for (Attendance bean : list) {
            if (users.get(bean.getUserId()) == null) {
                users.put(bean.getUserId().toString(), userService.find(bean.getUserId()));
            }
        }

        ModelAndView view = new  ModelAndView("/attend/list");
        view.addObject("users", users);
        view.addObject("attends", list);

        return view;
    }

    //将打卡机上的考勤日志，同步到考勤系统中
    @RequestMapping(path = "/sync", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return gson(100, "no upload file.");
        }

        //按天生成数据间隔
        File temp= new File(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());
        try {
            file.transferTo(temp);

//            context.getBean(ExcelService.class).syncToDatabase(temp.getAbsolutePath());
        } catch (IOException e) {
            return gson(101, e.getMessage());
        } finally {
            if (file != null) {
                temp.delete();
            }
        }

        return gson();
    }

    @RequestMapping(path = "/bymonth", method = RequestMethod.GET)
    public String syncByMonth(@RequestParam(value = "month", required = false) @DateTimeFormat(pattern="yyyy-MM") Date month,
                              @RequestParam(value = "date1", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                              @RequestParam(value = "date2", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date2
    ) {
        if (month == null && date1 == null && date2 == null) {
            //获取当前的时间
            if (null == ( month = DateTimeUtil.getDate(new Date(), "yyyy-MM") )) {
                return gson(100, "no month");
            }
        }

        if (month != null) {
            //将月份换为当月的起止日期
            Calendar calendar = DateTimeUtil.getCalendar(month);

            String str1 = new SimpleDateFormat("yyyy-MM-01").format(month);
            String str2 = new SimpleDateFormat(String.format("yyyy-MM-%s", calendar.getActualMaximum(Calendar.DAY_OF_MONTH))).format(month);

            date1 = DateTimeUtil.getDateByString(str1, DateTimeUtil.DATEFORTMATER);
            date2 = DateTimeUtil.getDateByString(str2, DateTimeUtil.DATEFORTMATER);
        }

        List<User> users  = context.getBean(UserService.class).find();
        if (users == null) {
            //没有任何用户
            return gson(130, "no user to sysc.");
        }

        for (User user : users) {
            //获取每个用户从当月第一天到最后一天所有的考勤记录，按每天最早打卡时间和最晚打卡时间来计算用户的考勤记录。比如：正常的上班时间，下班时间，加班时间等
            Calendar calendar = DateTimeUtil.getCalendar();
            calendar.setTime(date1);
            do {
                //每天的数据都丢给考勤系统处理
                syncByUser(user.getId(), calendar.getTime());

                calendar.add(Calendar.DATE, 1);
            } while (calendar.getTime().before(date2));
        }

        return gson();
    }


    @RequestMapping(path = "/byuser/{uid}/{date}", method = RequestMethod.GET)
    public String syncByUser(@PathVariable int uid, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        //得到指定月份第一天
        TimeLogService service  = context.getBean(TimeLogService.class);
        TimeLog log1            = service.getFirstTimeByUserAndDate(uid, date);
        TimeLog log2            = service.getLastTimeByUserAndDate(uid, date);

        //考勤当天首次打卡时间与最后一次打卡时间
        Date firstClock = null;
        Date lastClock  = null;

        if (log1 != null && Integer.valueOf(DateTimeUtil.getFullHour(log1.getTime())) < 12) {
            firstClock = log1.getTime();
        }

        if (log2 != null && Integer.valueOf(DateTimeUtil.getFullHour(log2.getTime())) >= 12) {
            lastClock = log2.getTime();
        }

        context.getBean(AttendanceService.class).syncByAttendance(uid, date, firstClock, lastClock);

        return gson();
    }


    // /byhands/3213/am or pm/2015-09-11/2015-09-12 02:59:59
    @RequestMapping(path = "/byhands", method = RequestMethod.GET)
    public String syncByUser(@RequestParam(value = "uid") Integer uid,
                             @RequestParam(value = "type") String type,
                             @RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                             @RequestParam(value = "time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") Date time
    ) {
        AttendanceService service = context.getBean(AttendanceService.class);
        Attendance bean = service.findByUserAndDate(uid, date);

        if (bean == null) {
            //无考勤记录
            return gson(100, "no attendance");
        }

        if (Boolean.TRUE.equals(bean.getLocked())) {
            return gson(101, "此条考勤记录已被锁定，无法修改.");
        }

        //没有指定，采用默认的时间
        if (time == null) {
            if (type.equalsIgnoreCase("am")) {
                //默认上午考勤时间
                time = DateTimeUtil.getDateByString(DateTimeUtil.getStringByDate(date, DateTimeUtil.DATEFORTMATER) + " 08:59:59");
            } else if (type.equalsIgnoreCase("pm")) {
                time = DateTimeUtil.getDateByString(DateTimeUtil.getStringByDate(date, DateTimeUtil.DATEFORTMATER) + " 18:00:01");
            }
        }


        if (false == service.reclockByHands(uid, date,
                type.equalsIgnoreCase("am") ?  time : null,
                type.equalsIgnoreCase("pm") ?  time : null
                )) {
            return gson(102, "修改不成功，无法更新考勤数据。");
        }

        return gson();
    }

    //手动设置某个用户的全勤记录，将所有的非正常的考勤记录全转换为正常的考勤记录
    @RequestMapping(path = "/onekeyok", method = RequestMethod.GET)
    public String onekeyok(@RequestParam(value = "uid") Integer uid,
                             @RequestParam(value = "date1") @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                             @RequestParam(value = "date2") @DateTimeFormat(pattern="yyyy-MM-dd") Date date2
    ) {
        if (date1 == null || date2 == null) {
            List<Date> lfDay = DateTimeUtil.getFirstAndLastDay(new Date());

            if (lfDay.get(0) != null) {
                date1 = lfDay.get(0);
            }

            if (lfDay.get(1) != null) {
                date2 = lfDay.get(1);
            }
        }

        //通过取得在date1和date2之间的考勤记录，与数据库中的上下班时间对比。如果不符合上下班时间，那么将时间调整为正常的上下班时间。
        //如果当天为节假日，者不需要调整
        Calendar calendar = DateTimeUtil.getCalendar(date1);

        WorkdaySettingService workdaySettingService = context.getBean(WorkdaySettingService.class);
        String ontime     = workdaySettingService.getWorkOnTime().toString();
        String offTime    = workdaySettingService.getWorkOffTime().toString();

        DaySettingService daySettingService = context.getBean(DaySettingService.class);
        AttendanceService service = context.getBean(AttendanceService.class);
        Attendance bean;
        Date workday;

        do {
            workday = calendar.getTime();
            if (false == daySettingService.isWorkDay(workday)) {
                calendar.add(Calendar.DATE, 1);
                //非工作日，不需要设置
                continue;
            }

            if ( null == ( bean = service.findByUserAndDate(uid, workday) )) {
                calendar.add(Calendar.DATE, 1);
                //没有对应的考勤日志，不需要设置
                continue;
            }

            if (Boolean.TRUE.equals(bean.getLocked())) {
                calendar.add(Calendar.DATE, 1);
                //记录被锁定，不允许修改
                continue;
            }

            //检查考勤中的日志是否符合考勤日志
            if (bean.getFirstClock() == null
                    || ontime.compareTo(new java.sql.Time(bean.getFirstClock().getTime()).toString()) < 0
                    ) {
                //需要调整上午打卡时间
                syncByUser(uid, "am", bean.getDate(), null);
            }

            if (bean.getLastClock() == null
                    || offTime.compareTo(new java.sql.Time(bean.getLastClock().getTime()).toString()) > 0
                    ) {
                //需要调整上午打卡时间
                syncByUser(uid, "pm", bean.getDate(), null);
            }

            calendar.add(Calendar.DATE, 1);
        } while (calendar.getTime().before(date2));

        return gson();
    }
}
