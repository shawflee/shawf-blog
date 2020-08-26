package com.shawf.dao;

import com.shawf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shawf_lee
 * @date 2020/5/26 14:00
 * Content:
 */
public interface UserDao extends JpaRepository<User,Long> {
    User findByUserNameAndPassword(String userName,String password);
}
