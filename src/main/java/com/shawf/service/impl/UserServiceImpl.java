package com.shawf.service.impl;

import com.shawf.dao.UserDao;
import com.shawf.entity.User;
import com.shawf.service.UserService;
import com.shawf.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shawf_lee
 * @date 2020/5/26 13:59
 * Content:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String userName, String password) {
        User user=userDao.findByUserNameAndPassword(userName, MD5Util.encrypByMd5(password));
        return user;
    }
}
