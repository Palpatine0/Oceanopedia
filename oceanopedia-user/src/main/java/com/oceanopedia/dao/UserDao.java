package com.oceanopedia.dao;

import com.oceanopedia.entity.User;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface UserDao {


    void saveUser(User user);

    void removeUserById(String id);

    List<User> findUsers(int page, int rows);

    User findUserByUsername(String username);

    User findUserById(String id);

    int countUser();

}
