package me.duanyong.blog.service;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by PP on 10/9/16.
 */

public abstract class AbstractService {


    protected SqlSessionFactory getSqlMapper() {
        return null;
    }
}
