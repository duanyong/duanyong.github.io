package me.duanyong.blog.service;

import me.duanyong.blog.bean.AbstractBean;
import java.util.List;

/**
 * Created by PP on 10/18/16.
 */

public interface Service<T extends AbstractBean> {

    T find(String key);
    T find(Integer key);
    List<T> find();
    List<T> find(Integer page, Integer size);

    Integer count();

    T insert(T bean);
    Boolean update(T bean);
    Boolean delete(Integer id);

}
