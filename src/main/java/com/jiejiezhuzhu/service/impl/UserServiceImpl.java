package com.jiejiezhuzhu.service.impl;

import com.jiejiezhuzhu.dao.UserDao;
import com.jiejiezhuzhu.domain.User;


import com.jiejiezhuzhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public boolean save(User user) {
        return userDao.save(user) > 0;
    }

    public boolean update(User user) {
        return userDao.update(user) > 0;
    }

    public boolean delete(Integer id) {
        return userDao.delete(id) > 0;
    }

    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public User getByName(User user) {
        return userDao.getByName(user);
    }

    public User getByNP(User user) {
        return userDao.getByNP(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }
}
