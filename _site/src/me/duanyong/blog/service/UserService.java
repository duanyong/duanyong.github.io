package me.duanyong.blog.service;

import me.duanyong.blog.bean.User;
import me.duanyong.blog.dao.TimeLogMapper;
import me.duanyong.blog.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import me.duanyong.handswork.util.DateTimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class UserService implements Service<User> {
    @Autowired
    private SqlSessionFactory sessionFactory;


    public User find(String name) {
//        SqlSession sqlSession = sessionFactory.openSession();
//
//        try {
//            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//            return mapper.selectByName(name);
//        } finally {
//            sqlSession.close();
//        }

        return null;
    }

    public User findByStaffno(String staffno) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            return mapper.selectByStaffno(staffno);
        } finally {
            sqlSession.close();
        }
    }

    public User find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<User> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<User> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public User importFromExcel(String nickname, String staffno, Date date) {
        User user = new User();
        user.setUsername("no@mail.com");
        user.setPassword("nopassword");
        user.setLastname(nickname.substring(1));
        user.setFirstname(nickname.substring(0, 1));
        user.setStaffno(staffno);
        user.setTime(new Date());
        user.setDesc("Excel导入");
        user.setStatus((byte) 0);

        return insert(user);
    }

    public User insert(User bean) {
        SqlSession session = sessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(User bean) {
        SqlSession session = sessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

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
