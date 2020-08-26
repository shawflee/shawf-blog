package com.shawf.service;

import com.shawf.entity.User;

/**
 * @author shawf_lee
 * @date 2020/5/26 13:57
 * Content:
 */
public interface UserService {

    User checkUser(String userName,String password);
}
