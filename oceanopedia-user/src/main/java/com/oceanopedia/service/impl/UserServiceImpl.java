package com.oceanopedia.service.impl;


import com.oceanopedia.dao.UserDao;
import com.oceanopedia.entity.User;
import com.oceanopedia.service.UserService;
import com.oceanopedia.vo.OceanopediaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    // aboard after spring security configured, this method wont execute anymore
    @Override
    public OceanopediaResult login(String username, String password, String phone, String verificationCode) {
        /*LoginLog loginLog = new LoginLog();
        loginLog.setUsername(phone);
        loginLog.setLoginTime(new Date());
        loginLog.setType("1");*/

        User user = userDao.findUserByUsername(username);
        String name = user.getUsername();
        String pwd = user.getPassword();

        if (name == null) {
            System.out.println("username failed");
            return OceanopediaResult.error("Enter your username");
        }
        if (password == null) {
            System.out.println("pwd failed");
            return OceanopediaResult.error("Enter your password");
        }
        if (!password.equals(pwd)) {
            System.out.println("username unmatched");
            return OceanopediaResult.error("Username or password code does not match");
        }

        /*loginLog.setSuccess(true);
        loginLog.setMessage("Successfully sign in");*/

        /*loginLogDao.insertLoginLog(loginLog);*/
        return OceanopediaResult.ok("Successfully sign in");
    }

    @Override
    public OceanopediaResult register(String username, String password, String avatar, String type) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setType(type);
        if (username == "") {
            return OceanopediaResult.error("Username cannot be empty");
        } else if (password == "") {
            return OceanopediaResult.error("Password cannot be empty");
        }
        User user4Check = userDao.findUserByUsername(username);
        if (user4Check != null) {
            return OceanopediaResult.error("This username had been used");
        }

        userDao.saveUser(user);
        OceanopediaResult ok = OceanopediaResult.ok();
        ok.setMsg("Successfully registered");
        return ok;
    }

    @Override
    public OceanopediaResult deleteUserById(String id) {
        userDao.removeUserById(id);
        if (id == null) {
            OceanopediaResult error = OceanopediaResult.error();
            error.setMsg("Failed deleted, id cannot be empty");
            return error;
        }
        OceanopediaResult ok = OceanopediaResult.ok();
        ok.setMsg("Successfully deleted");
        return ok;
    }

    @Override
    public OceanopediaResult getUsers(int page, int rows) {
        List<User> usersList = userDao.findUsers(page, rows);
        OceanopediaResult ok = new OceanopediaResult();
        ok.setCnt(userDao.countUser());
        ok.setData(usersList);
        return ok;
    }

    @Override
    public OceanopediaResult getUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return OceanopediaResult.ok(user);
    }

    @Override
    public OceanopediaResult getUserById(String id) {
        User user = userDao.findUserById(id);
        return OceanopediaResult.ok(user);
    }

}
