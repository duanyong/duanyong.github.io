package me.duanyong.blog.service;

import me.duanyong.blog.bean.Attendance;
import me.duanyong.blog.bean.OvertimeSetting;
import me.duanyong.blog.bean.TimeLog;
import me.duanyong.blog.bean.WorkdaySetting;
import me.duanyong.blog.dao.AttendanceMapper;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import me.duanyong.handswork.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class AttendanceService implements Service<Attendance> {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private WebApplicationContext context;


    public Attendance find(String name) {
        return null;
    }

    public Attendance find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<Attendance> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<Attendance> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public Attendance insert(Attendance bean) {
        SqlSession session = sessionFactory.openSession();

        AttendanceMapper mapper = session.getMapper(AttendanceMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(Attendance bean) {
        SqlSession session = sessionFactory.openSession();

        AttendanceMapper mapper = session.getMapper(AttendanceMapper.class);

        try {
            return mapper.updateByPrimaryKey(bean) > 0;
        } finally {
            session.close();
        }
    }


    public Boolean delete(Integer id) {
        return null;
    }


    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义函数
    //

    //返回当月的考勤信息
    public List<Attendance> findByMonth(Date date1, Date date2, Integer userId, int page, int size) {
        SqlSession sqlSession = sessionFactory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);

        try {
            return mapper.selectListInRange(date1, date2, userId);
        } finally {
            sqlSession.close();
        }
    }

    //返回当月的考勤信息
    public List<Attendance> findOnlyBadByMonth(Date date1, Date date2, Integer userId, int page, int size) {
        SqlSession sqlSession = sessionFactory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);

        try {
            return mapper.selectBadListInRange(null, null, date1, date2, userId);
        } finally {
            sqlSession.close();
        }
    }


    //查看某天的考勤记录
    public Attendance findByUserAndDate(int uid, Date date) {
        SqlSession sqlSession = sessionFactory.openSession();

        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
        try {
            return mapper.selectByUserAndDate(uid, date);
        } finally {
            sqlSession.close();
        }
    }

    //检查用户的加班情况
    //  指定某一天，检查用户前一天是否加班，并且将加班之后的考勤信息换算为可以延迟的打卡时间以秒数返回
    //  判断标准：
    //      1, 当天是否为工作日
    //      2, 工作日的情况下有以下三种情况：
    //          1) 昨天晚于22点打卡，第二天可早上10点钟到公司
    //          2) 昨天晚于24点打卡，第二天可下午13点钟到公司
    //          3) 昨天晚于02点打卡，第二天可不到公司
    //
    //  用户没有加班情况，直接返回0
    public int getOvertimeSeconds(int uid, Date date) {
        if (false == context.getBean(DaySettingService.class).matchTypeByDate(context.getBean(CategoryService.class).getIdByName("考勤标签", "工作日"), date)) {
            //非工作日
            return 0;
        }

//        List<OvertimeSetting> ots = context.getBean(OvertimeSettingService.class).find();
//
//        if (ots == null || ots.size() == 0) {
//            return 0;
//        }

        //获取正常的工作时间设定

        //得到今天最晚的打卡日志
        TimeLog timeLog = context.getBean(TimeLogService.class).getLastTimeByUserAndDate(uid, date);
        if (timeLog != null && false == timeLog.getTime().equals(null)) {
            int hour = Integer.valueOf(DateTimeUtil.getFullHour(timeLog.getTime()));
            if (hour >= 22) {
                log.info("当天加班在22点之后，具体打卡时间：" + DateTimeUtil.getStringByDate(timeLog.getTime()));
            }

            return  hour >= 22 ?  1 * 60 * 60 : 0;
        }

        //获取第二的打卡信息
        Calendar calender = DateTimeUtil.getCalendar(date);
        calender.add(Calendar.DATE, +1);            //将日期提前一天

        timeLog = context.getBean(TimeLogService.class).getFirstTimeByUserAndDate(uid, calender.getTime());
        if (timeLog != null && false == timeLog.getTime().equals(null)) {
            int hour = Integer.valueOf(DateTimeUtil.getFullHour(timeLog.getTime()));

            //2点到7点之间打卡，算头天加班
            if (hour >= 2 && hour < 8) {
                //每天都不用上班
                log.info("头天加班在2点到8点，具体打卡时间：" + DateTimeUtil.getStringByDate(calender.getTime()));
                return 8 * 3600;
            } else if (hour >= 0 && hour < 2) {
                log.info("头天加班在0点到2点，具体打卡时间：" + DateTimeUtil.getStringByDate(calender.getTime()));

                return 4 * 3600;
            } else {
                //第二天正常打卡
                return 0;
            }
        }

        return 0;
    }


    //同步考勤记录
    //  只判断一天当中是否有两次打卡记录，如何有，不管打卡时间如何，表示正常。
    public Boolean syncByAttendance(int uid, Date date, Date time1, Date time2) {
//        if (false == context.getBean(DaySettingService.class).isWorkDay(date)) {
//            //非工作日，不算考勤记录
//            return false;
//        }

//        //获取今天的加班时间
//        int otMins = getOvertimeSeconds(uid, date);

        //用户考勤异常，只建立考勤记录，不做其它任何操作
        Attendance bean = findByUserAndDate(uid, date);
        boolean isExist = bean != null;

        if (null != bean && Boolean.TRUE.equals(bean.getLocked())) {
            //记录已经被锁，无法解开
            return false;
        }

        if (time1 == null && time2 == null) {
            //当天未打卡
            if (isExist == false) {
                bean = new Attendance();
            }

            bean.setDate(date);
            bean.setUserId(uid);
            bean.setTypeId(context.getBean(DaySettingService.class).getTypeByDate(date));


//            bean.setDesc("无记录，考勤异常");
//            bean.setTime(new Date());
//            bean.setStatus((byte) 1);


            return isExist ? update(bean) : insert(bean) != null;
        }

        //当天未打卡
        if (isExist == false) {
            bean = new Attendance();
        }

        bean.setDate(date);
        bean.setUserId(uid);
        bean.setTypeId(context.getBean(DaySettingService.class).getTypeByDate(date));

        SimpleDateFormat dateFormat = DateTimeUtil.getDateFormat(DateTimeUtil.DATEFORTMATER);
        SimpleDateFormat timeFormat = DateTimeUtil.getDateFormat(DateTimeUtil.TIMEFORTMATER);

        if (time1 != null) {
            bean.setFirstClock(DateTimeUtil.getDateByString(dateFormat.format(date) + " " + timeFormat.format(time1)));
        }

        if (time2 != null) {
            bean.setLastClock(DateTimeUtil.getDateByString(dateFormat.format(date) + " " + timeFormat.format(time2)));
        }

        return isExist ? update(bean) : insert(bean) != null;
    }

    public boolean tinkerupByStatus(int uid, Date date) {
        Attendance bean = findByUserAndDate(uid, date);
        if (bean == null) {
            return false;
        }

        //获取上下班设置
        Date amOnTime     = context.getBean(WorkdaySettingService.class).getWorkOnTime();
        Date pmOffTime    = context.getBean(WorkdaySettingService.class).getWorkOffTime();

        if (amOnTime == null || pmOffTime == null) {
            return false;
        }

        Date amTimeClock = bean.getFirstReclock() != null ? bean.getFirstReclock() : bean.getFirstClock();
        Date pmTimeClock = bean.getLastReclock() != null ? bean.getLastReclock() : bean.getLastClock();

        if (amTimeClock == null) {
            //上午未打卡
            bean.setStatus(context.getBean(CategoryService.class).getIdByName("考勤标签", "旷工"));
            bean.setDesc("上午未打卡");
        } else {
            //重新格式化时间，将日期的时间戳去除，好用java.sql.Time的after或者after比较
            amTimeClock = DateTimeUtil.getDateByString(amTimeClock.toString(), DateTimeUtil.TIMEFORTMATER);

            if (amTimeClock.after(amOnTime)) {
                //打卡晚于上班规定的时间
                bean.setStatus(context.getBean(CategoryService.class).getIdByName("考勤标签", "迟到"));
                bean.setDesc("上午迟到");
            }
        }

        if (amTimeClock == null) {
            //下午未打卡
            bean.setStatus(context.getBean(CategoryService.class).getIdByName("考勤标签", "旷工"));
            bean.setDesc("下午未打卡");
        } else {
            //重新格式化时间，将日期的时间戳去除，好用java.sql.Time的after或者after比较
            amTimeClock = DateTimeUtil.getDateByString(pmTimeClock.toString(), DateTimeUtil.TIMEFORTMATER);

            if (amTimeClock.before(amOnTime)) {
                //打卡晚于上班规定的时间
                bean.setStatus(context.getBean(CategoryService.class).getIdByName("考勤标签", "早退"));
                bean.setDesc("下午早退");
            }
        }

        //查看是否有加班信息
        if (amTimeClock != null) {
            //获取加班设置，与当天的打卡时间一一对比，如果在设定时间之后，表示属于加班时间
            List<OvertimeSetting> list = context.getBean(OvertimeSettingService.class).getOvertimes();

            for (OvertimeSetting setting : list) {
                if (amTimeClock.after(setting.getAfter())) {
                    bean.setOverTime(setting.getAfter());
                    bean.setDesc("加班到：" + setting.getAfter().toString());

                    break;
                }
            }
        }


        return true;

//
//
//        byte errcode = 0;
//        String errmsg = "";
//
//        //检查是否有两次打卡记录
//        if (bean.getFirstClock() == null && bean.getFirstReclock() == null) {
//            errcode = 1;
//            errmsg  = "考勤记录需要调整";
//        } else if (bean.getLastClock() == null && bean.getLastReclock() == null) {
//            errcode = 1;
//            errmsg  = "考勤记录需要调整";
//        } else if (bean.getFirstClock().compareTo(bean.getLastClock()) == 0) {
//            //两次打卡时间一致，表示只打了一次卡
//            errcode = 0;
//            errmsg  = "考勤记录需要调整";
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("hh");
//        boolean lated = Integer.parseInt(dateFormat.format(bean.getFirstClock())) < 9;
//        boolean early = Integer.parseInt(dateFormat.format(bean.getLastClock())) < 18;
//
//        if (lated) {
//            //迟到
//            errcode = 10;
//            errmsg  = "迟到";
//
//        } else if (early) {
//            //早退
//            errcode = 10;
//            errmsg  = "早退";
//        }
//
//        bean.setStatus(errcode);
//        bean.setDesc(errmsg);
//
//        return update(bean);
    }


    public boolean syncOvertimeMinsByUserAndDate(int uid, Date date) {
        Attendance bean = findByUserAndDate(uid, date);
        if (bean.getStatus().equals(0) == false) {
            //非正常的考勤记录，忽略
            return false;
        }


        if (bean.getLastClock().equals(null)) {
            //下班没有打卡
            if (bean.getLastReclock().equals(null)) {
                //考勤未调整
                return false;
            }

            bean.setLastClock(bean.getLastReclock());
        }

        //获取正常的下班时间
        Date offworkTime = DateTimeUtil.getDate(new SimpleDateFormat("yyyy-MM-dd").format(date) + " 18:00:00");
        long otMins = ( bean.getLastClock().getTime() - offworkTime.getTime() ) / 1000;

        if (otMins < 0) {
            //在加班之前打卡，不记录
            otMins = 0;
        }

//        bean.setOverMins((int) otMins);

        return update(bean);
    }


    //手动调整打卡时间
    public boolean reclockByHands(int uid, Date date, Date am, Date pm) {
        Attendance bean = findByUserAndDate(uid, date);

        if (bean == null) {
            return false;
        }

        if (am == null && pm == null) {
            return false;
        }

        if (am != null) {
            bean.setFirstReclock(am);
        }

        if (pm != null) {
            bean.setLastReclock(pm);
        }

        return update(bean);
    }


    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////
}
