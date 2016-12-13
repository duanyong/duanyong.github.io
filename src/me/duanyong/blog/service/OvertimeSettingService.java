package me.duanyong.blog.service;

import me.duanyong.blog.bean.OvertimeSetting;
import me.duanyong.blog.dao.OvertimeSettingMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class OvertimeSettingService implements Service<OvertimeSetting> {
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Override
    public OvertimeSetting find(String key) {
        return null;
    }

    @Override
    public OvertimeSetting find(Integer key) {
        return null;
    }

    @Override
    public List<OvertimeSetting> find() {
        return null;
    }

    @Override
    public List<OvertimeSetting> find(Integer page, Integer size) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    public OvertimeSetting insert(OvertimeSetting bean) {
        SqlSession session = sessionFactory.openSession();

        OvertimeSettingMapper mapper = session.getMapper(OvertimeSettingMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(OvertimeSetting bean) {
        SqlSession session = sessionFactory.openSession();

        OvertimeSettingMapper mapper = session.getMapper(OvertimeSettingMapper.class);

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

    public List<OvertimeSetting> getOvertimes() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            OvertimeSettingMapper mapper = sqlSession.getMapper(OvertimeSettingMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////
}
