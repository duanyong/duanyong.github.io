package me.duanyong.blog.service;

import me.duanyong.blog.bean.TimeCard;
import me.duanyong.blog.dao.TimeCardMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class TimeCardService implements Service<TimeCard> {
    @Autowired
    private SqlSessionFactory sessionFactory;


    public TimeCard findByUserAndCard(Integer userId, String cardNo) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeCardMapper mapper = sqlSession.getMapper(TimeCardMapper.class);

            return mapper.selectByUserAndCard(userId, cardNo);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public TimeCard find(String key) {
        return null;
    }

    public TimeCard find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeCardMapper mapper = sqlSession.getMapper(TimeCardMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<TimeCard> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeCardMapper mapper = sqlSession.getMapper(TimeCardMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<TimeCard> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            TimeCardMapper mapper = sqlSession.getMapper(TimeCardMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public TimeCard importFromExcel(Integer userId, String cardNo) {
        TimeCard timeCard = new TimeCard();
        timeCard.setCardno(cardNo);
        timeCard.setUserId(userId);
        timeCard.setDesc("Excel导入");
        timeCard.setTime(new Date());
        timeCard.setStatus((byte) 0);

        return insert(timeCard);
    }

    public TimeCard insert(TimeCard bean) {
        SqlSession session = sessionFactory.openSession();

        TimeCardMapper mapper = session.getMapper(TimeCardMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(TimeCard bean) {
        SqlSession session = sessionFactory.openSession();

        TimeCardMapper mapper = session.getMapper(TimeCardMapper.class);

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
