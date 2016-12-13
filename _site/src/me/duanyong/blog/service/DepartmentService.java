package me.duanyong.blog.service;

import me.duanyong.blog.bean.Department;
import me.duanyong.blog.bean.DepartmentStaff;
import me.duanyong.blog.bean.User;
import me.duanyong.blog.dao.DepartmentMapper;
import me.duanyong.blog.dao.DepartmentStaffMapper;
import me.duanyong.blog.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class DepartmentService implements Service<Department> {
    @Autowired
    private SqlSessionFactory sessionFactory;


    public Department find(String name) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            return mapper.selectByName(name);
        } finally {
            sqlSession.close();
        }
    }

    public Department find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public List<Department> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public List<Department> find(Integer page, Integer size) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public Integer count() {
        return null;
    }

    public Department importFromExcel(String name) {
        Department department = new Department();
        department.setName(name);
        department.setDesc("Excel导入");
        department.setTime(new Date());
        department.setStatus((byte) 0);

        return insert(department);
    }

    public Department insert(Department bean) {
        SqlSession session = sessionFactory.openSession();

        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(Department bean) {
        SqlSession session = sessionFactory.openSession();

        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);

        try {
            return mapper.updateByPrimaryKey(bean) == 0;
        } finally {
            session.close();
        }
    }

    public Boolean delete(Integer id) {
        return null;
    }

    public List<User> listByDepartmentId(Integer departmentId) {
        SqlSession sqlSession = sessionFactory.openSession();

        List<User> users = new ArrayList<>();
        try {
            DepartmentStaffMapper staffMapper = sqlSession.getMapper(DepartmentStaffMapper.class);
            List<DepartmentStaff> staffs = staffMapper.listStaffByDepartmentId(departmentId);

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            for (DepartmentStaff staff : staffs) {
                users.add(userMapper.selectByPrimaryKey(staff.getUserId()));
            }
        } finally {
            sqlSession.close();
        }

        return users;
    }
}
