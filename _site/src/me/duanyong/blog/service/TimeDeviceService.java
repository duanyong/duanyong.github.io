package me.duanyong.blog.service;

import me.duanyong.blog.bean.TimeDevice;
import me.duanyong.blog.dao.TimeDeviceMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class TimeDeviceService implements Service<TimeDevice> {
    @Autowired
    private SqlSessionFactory sessionFactory;


    public TimeDevice find(String sn) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeDeviceMapper mapper = sqlSession.getMapper(TimeDeviceMapper.class);

            return mapper.selectBySn(sn);
        } finally {
            sqlSession.close();
        }
    }

    public TimeDevice find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeDeviceMapper mapper = sqlSession.getMapper(TimeDeviceMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<TimeDevice> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeDeviceMapper mapper = sqlSession.getMapper(TimeDeviceMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<TimeDevice> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeDeviceMapper mapper = sqlSession.getMapper(TimeDeviceMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public TimeDevice importFromExcel(String deviceSn, String title) {
        TimeDevice timeDevice = new TimeDevice();
        timeDevice.setSn(deviceSn);
        timeDevice.setTitle(title);

        timeDevice.setDesc("Excel导入");
        timeDevice.setTime(new Date());
        timeDevice.setStatus((byte) 0);

        return insert(timeDevice);
    }

    public TimeDevice insert(TimeDevice bean) {
        SqlSession session = sessionFactory.openSession();

        TimeDeviceMapper mapper = session.getMapper(TimeDeviceMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(TimeDevice bean) {
        SqlSession session = sessionFactory.openSession();

        TimeDeviceMapper mapper = session.getMapper(TimeDeviceMapper.class);

        try {
            return mapper.updateByPrimaryKey(bean) == 0;
        } finally {
            session.close();
        }
    }

    public Boolean delete(Integer id) {
        return null;
    }
}
