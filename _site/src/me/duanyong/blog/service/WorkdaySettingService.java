package me.duanyong.blog.service;

import me.duanyong.blog.bean.WorkdaySetting;
import me.duanyong.blog.dao.WorkdaySettingMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.*;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class WorkdaySettingService implements Service<WorkdaySetting> {
    @Autowired
    private SqlSessionFactory sessionFactory;


    public WorkdaySetting find(String name) {
        return null;
    }

    public WorkdaySetting find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            WorkdaySettingMapper mapper = sqlSession.getMapper(WorkdaySettingMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<WorkdaySetting> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            WorkdaySettingMapper mapper = sqlSession.getMapper(WorkdaySettingMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<WorkdaySetting> find(Integer page, Integer size) {
        return null;
    }

    public Integer count() {
        return null;
    }

    public WorkdaySetting insert(WorkdaySetting bean) {
        SqlSession session = sessionFactory.openSession();

        WorkdaySettingMapper mapper = session.getMapper(WorkdaySettingMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(WorkdaySetting bean) {
        SqlSession session = sessionFactory.openSession();

        WorkdaySettingMapper mapper = session.getMapper(WorkdaySettingMapper.class);

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

    public List<WorkdaySetting> findByType(Integer typeId) {
        SqlSession sqlSession = sessionFactory.openSession();

        WorkdaySettingMapper mapper = sqlSession.getMapper(WorkdaySettingMapper.class);

        try {
            return mapper.selectByType(typeId);
        } finally {
            sqlSession.close();
        }
    }


    //返回某天的工作时间设计，date必须只能为 2011-01-01 或 2011-01-01 00:00:00 的信息，如果包括小时信息，返回的数据会出现偏移
    public Map<String, WorkdaySetting> findByType(Integer typeId, Date date) {
        List<WorkdaySetting> settings = findByType(typeId);

        if (null == settings) {
            return null;
        }

        Map<String, WorkdaySetting> map = new HashMap<>();
        for (WorkdaySetting setting : settings) {
            //将当前的日期信息添加到bean元素中
            //通过计算日期的时间戳 + 9点的时间戳
            setting.setStartTime(new Date(date.getTime() + setting.getStartTime().getTime()));

            //通过计算日期的时间戳 + 18点的时间戳
            setting.setStopTime(new Date(date.getTime() + setting.getStopTime().getTime()));

            map.put(setting.getName(), setting);
        }

        return map;
    }


    //获取上班时间
    public Time getWorkOnTime() {
        List<WorkdaySetting> settings = findByType(1);

        if (settings == null || settings.size() == 0) {
            return null;
        }

        for (WorkdaySetting setting : settings) {
            if (setting.getName().equals("am")) {
                return new java.sql.Time(setting.getStartTime().getTime());
            }
        }

        return null;
    }

    //获取上班时间
    public Time getWorkOffTime() {
        List<WorkdaySetting> settings = findByType(1);

        if (settings == null || settings.size() == 0) {
            return null;
        }

        for (WorkdaySetting setting : settings) {
            if (setting.getName().equals("pm")) {
                return new java.sql.Time(setting.getStopTime().getTime());
            }
        }

        return null;
    }


    //查看某天是否为工作日
    public static boolean isWorkday(Date date) {

        return false;
    }

    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////
}
