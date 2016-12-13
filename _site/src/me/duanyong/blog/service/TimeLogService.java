package me.duanyong.blog.service;

import me.duanyong.blog.bean.TimeLog;
import me.duanyong.blog.dao.TimeLogMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class TimeLogService implements Service<TimeLog> {
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private WebApplicationContext context;


    public TimeLog find(String key) {
        return null;
    }

    public TimeLog find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeLogMapper mapper = sqlSession.getMapper(TimeLogMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<TimeLog> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeLogMapper mapper = sqlSession.getMapper(TimeLogMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<TimeLog> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeLogMapper mapper = sqlSession.getMapper(TimeLogMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public TimeLog insert(TimeLog bean) {
        SqlSession session = sessionFactory.openSession();

        TimeLogMapper mapper = session.getMapper(TimeLogMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(TimeLog bean) {
        SqlSession session = sessionFactory.openSession();

        TimeLogMapper mapper = session.getMapper(TimeLogMapper.class);

        try {
            return mapper.updateByPrimaryKey(bean) == 0;
        } finally {
            session.close();
        }
    }

    public TimeLog importFromExcel(Integer userId, Integer deviceId, Integer cardId, Date date, Date time, String desc) {
        TimeLog timeLog = new TimeLog();
        timeLog.setUserId(userId);
        timeLog.setTimeCardId(cardId);
        timeLog.setTimeDeviceId(deviceId);

        timeLog.setDate(date);
        timeLog.setTime(time);
        timeLog.setDesc("Excel导入:" + desc);
        timeLog.setStatus((byte) 0);

        return insert(timeLog);
    }

    public Boolean delete(Integer id) {
        return null;
    }

    public TimeLog findByUserAndTime(Integer userId, Date date, Date time) {
        SqlSession session = sessionFactory.openSession();

        TimeLogMapper mapper = session.getMapper(TimeLogMapper.class);

        try {
            return mapper.selectByUserAndTime(userId, date, time);

        } finally {
            session.close();
        }
    }

    public TimeLog getFirstTimeByUserAndDate(Integer userId, Date date) {
        SqlSession session = sessionFactory.openSession();

        TimeLogMapper mapper = session.getMapper(TimeLogMapper.class);

        try {
            return mapper.getFirstTimeByUserAndDate(userId, date);
        } finally {
            session.close();
        }
    }


    public TimeLog getLastTimeByUserAndDate(Integer userId, Date date) {
        SqlSession session = sessionFactory.openSession();

        TimeLogMapper mapper = session.getMapper(TimeLogMapper.class);

        try {
            return mapper.getLastTimeByUserAndDate(userId, date);
        } finally {
            session.close();
        }
    }
}
