package me.duanyong.blog.service;

import me.duanyong.blog.bean.Category;
import me.duanyong.blog.bean.DaySetting;
import me.duanyong.blog.dao.DaySettingMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class DaySettingService implements Service<DaySetting> {
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private WebApplicationContext context;


    public DaySetting find(String name) {
        return null;
    }

    public DaySetting find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DaySettingMapper mapper = sqlSession.getMapper(DaySettingMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<DaySetting> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DaySettingMapper mapper = sqlSession.getMapper(DaySettingMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<DaySetting> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DaySettingMapper mapper = sqlSession.getMapper(DaySettingMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public DaySetting insert(DaySetting bean) {
        SqlSession session = sessionFactory.openSession();

        DaySettingMapper mapper = session.getMapper(DaySettingMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(DaySetting bean) {
        SqlSession session = sessionFactory.openSession();

        DaySettingMapper mapper = session.getMapper(DaySettingMapper.class);

        try {
            return mapper.updateByPrimaryKey(bean) == 0;
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

    public DaySetting find(Date date) {
        SqlSession sqlSession = sessionFactory.openSession();
        DaySettingMapper mapper = sqlSession.getMapper(DaySettingMapper.class);

        try {
            return mapper.findByDate(date);
        } finally {
            sqlSession.close();
        }
    }

    //查看某天是符合type指定的类型
    public Boolean matchTypeByDate(int typeId, Date date) {
        SqlSession sqlSession = sessionFactory.openSession();
        DaySettingMapper mapper = sqlSession.getMapper(DaySettingMapper.class);

        try {
            DaySetting setting = mapper.findByDate(date);

            return setting != null && setting.getTypeId() != null && setting.getTypeId().equals(typeId);
        } finally {
            sqlSession.close();
        }
    }

    //查看是否为工作日
    public Boolean isWorkDay(Date date) {
        Integer typeId = context.getBean(CategoryService.class).getIdByName("考勤标签", "工作日");
        if (typeId == null) {
            return false;
        }

        return matchTypeByDate(typeId, date);
    }

    public Integer getTypeByDate(Date date) {
        DaySetting setting = find(date);

        return setting == null ? null : setting.getTypeId();
    }

    public Boolean setDayType(Date date, Integer typeId, float scale) {
        DaySetting setting = find(date);

        if (setting == null) {
            setting = new DaySetting();
            setting.setTypeId(typeId);
            setting.setUserId(0);
            setting.setDate(date);
            setting.setAlias("");
            setting.setScale(scale);
            setting.setDesc("");

            return insert(setting) != null;
        }

        //需要更新数据
        setting.setTypeId(typeId);
        setting.setScale(scale);

        return update(setting);
    }

    public void initByYear(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1, 0, 0, 0);  //year所对应的年份第一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.YEAR));

        int wkDay   = 0;
        int maxDay  = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);

        Category goodDay    = context.getBean(CategoryService.class).findByName("考勤标签", "工作日");
        Category badDay     = context.getBean(CategoryService.class).findByName("考勤标签", "周末");

        if (goodDay == null || badDay == null) {
            return ;
        }

        while (( -- maxDay ) >= 0) {

            //获取周几
            wkDay = calendar.get(Calendar.DAY_OF_WEEK);

            //若一周第一天为星期天，则-1
            if (isFirstSunday) {
                wkDay = wkDay - 1;
                if (wkDay == 0) {
                    wkDay = 7;
                }
            }

            if (wkDay <= 5) {
                //工作日
                setDayType(calendar.getTime(), goodDay.getId(), Float.valueOf(goodDay.getAlias()));
            } else {
                setDayType(calendar.getTime(), badDay.getId(), Float.valueOf(badDay.getAlias()));
            }

            //查看当天是星期几，由于来设置是否是工作日
            calendar.add(Calendar.DATE, 1);
        }
    }

    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////
}
