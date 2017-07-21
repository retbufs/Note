package com.chens.dao;

import com.chens.dao.annotation.MyBatisAnnotation;
import com.chens.entry.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */
@Component
@MyBatisAnnotation
public interface UserDao {
    public User findByName(String name);
    public void addUser(User user);
    public void uppass(User user);
    public User findById(String  id);
    public int changPass(User user);
}
