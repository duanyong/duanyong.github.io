package me.duanyong.blog.service;

import me.duanyong.blog.bean.Category;
import me.duanyong.blog.dao.CategoryMapper;
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
public class CategoryService implements Service<Category> {
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private WebApplicationContext context;


    public Category find(String name) {
        return null;
    }

    public Category find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<Category> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<Category> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public Category insert(Category bean) {
        SqlSession session = sessionFactory.openSession();

        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(Category bean) {
        SqlSession session = sessionFactory.openSession();

        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

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


    //查看某天是符合type指定的类型
    public Integer getIdByName(String topName, String name) {
        Category category = findByName(topName, 0);

        if (category == null) {
            return null;
        }

        category = findByName(name, category.getId());

        if (category == null) {
            return null;
        }

        return category.getId();
    }


    //查看某天是符合type指定的类型
    public Category findByName(String topName, String name) {
        Category category = findByName(topName, 0);

        if (category == null) {
            return null;
        }

        return findByName(name, category.getId());
    }

    //查看某天是符合type指定的类型
    public Category findByName(String name, Integer topId) {
        SqlSession sqlSession = sessionFactory.openSession();
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

        try {
            return mapper.findByName(name, topId);
        } finally {
            sqlSession.close();
        }
    }


    //
    // 自定义函数
    ////////////////////////////////////////////////////////////////////////////////////////
}
