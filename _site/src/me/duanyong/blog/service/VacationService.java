package me.duanyong.blog.service;

import me.duanyong.blog.bean.Vacation;
import me.duanyong.blog.dao.VacationMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class VacationService {
    @Autowired
    private SqlSessionFactory sessionFactory;

    public List<Vacation> findByUserAndDate(Integer userId, Date date) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            VacationMapper mapper = sqlSession.getMapper(VacationMapper.class);

            return mapper.selectByUserAndDate(userId, date);
        } finally {
            sqlSession.close();
        }
    }


    public List<Vacation> findByUserAndDateAndType(Integer userId, Date date, Integer typeId) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            VacationMapper mapper = sqlSession.getMapper(VacationMapper.class);

            return mapper.selectByUserAndDateAndType(userId, date, typeId);
        } finally {
            sqlSession.close();
        }
    }

    public Vacation insert(Vacation bean) {
        SqlSession session = sessionFactory.openSession();

        VacationMapper mapper = session.getMapper(VacationMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(Vacation bean) {
        SqlSession session = sessionFactory.openSession();

        VacationMapper mapper = session.getMapper(VacationMapper.class);

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
