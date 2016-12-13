package me.duanyong.blog.service;

import me.duanyong.blog.bean.DepartmentStaff;
import me.duanyong.blog.dao.DepartmentStaffMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by PP on 10/9/16.
 */
@org.springframework.stereotype.Service
public class DepartmentStaffService implements Service<DepartmentStaff> {
    @Autowired
    private SqlSessionFactory sessionFactory;


    public DepartmentStaff find(String name) {
//        SqlSession sqlSession = sessionFactory.openSession();
//
//        try {
//            DepartmentStaffMapper mapper = sqlSession.getMapper(DepartmentStaffMapper.class);
//
//            return mapper.selectByName(name);
//        } finally {
//            sqlSession.close();
//        }

        return null;
    }

    public DepartmentStaff find(Integer key) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentStaffMapper mapper = sqlSession.getMapper(DepartmentStaffMapper.class);

            return mapper.selectByPrimaryKey(key);
        } finally {
            sqlSession.close();
        }
    }

    public DepartmentStaff findStaff(Integer departmentId, Integer userId) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentStaffMapper mapper = sqlSession.getMapper(DepartmentStaffMapper.class);

            return mapper.selectByDepartmentAndUser(departmentId, userId);
        } finally {
            sqlSession.close();
        }
    }

    public List<DepartmentStaff> find() {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentStaffMapper mapper = sqlSession.getMapper(DepartmentStaffMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<DepartmentStaff> find(Integer page, Integer size) {
        return null;
    }

    public Integer count() {
        return null;
    }

    public DepartmentStaff importFromExcel(Integer departmentId, Integer userId) {
        DepartmentStaff staff = new DepartmentStaff();
        staff.setUserId(userId);
        staff.setDepartmentId(departmentId);

        staff.setDesc("Excel导入");
        staff.setTime(new Date());
        staff.setStatus((byte) 0);

        return insert(staff);
    }

    public DepartmentStaff insert(DepartmentStaff bean) {
        SqlSession session = sessionFactory.openSession();

        DepartmentStaffMapper mapper = session.getMapper(DepartmentStaffMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            session.close();
        }
    }

    public Boolean update(DepartmentStaff bean) {
        SqlSession session = sessionFactory.openSession();

        DepartmentStaffMapper mapper = session.getMapper(DepartmentStaffMapper.class);

        try {
            return mapper.updateByPrimaryKey(bean) == 0;
        } finally {
            session.close();
        }
    }

    public Boolean delete(Integer id) {
        return null;
    }

    public List<DepartmentStaff> listByDepartId(Integer departmentId) {
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            DepartmentStaffMapper mapper = sqlSession.getMapper(DepartmentStaffMapper.class);

            return mapper.listStaffByDepartmentId(departmentId);
        } finally {
            sqlSession.close();
        }
    }
}
