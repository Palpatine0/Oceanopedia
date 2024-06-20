package com.oceanopedia.service.impl;


import com.oceanopedia.dao.UserDao;
import com.oceanopedia.entity.User;
import com.oceanopedia.service.UserService;
import com.oceanopedia.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    // aboard after spring security configured, this method wont execute anymore
    @Override
    public BaseResult login(String username, String password, String phone, String verificationCode) {
        /*LoginLog loginLog = new LoginLog();
        loginLog.setUsername(phone);
        loginLog.setLoginTime(new Date());
        loginLog.setType("1");*/

        User user = userDao.findUserByUsername(username);
        String name = user.getUsername();
        String pwd = user.getPassword();

        if (name == null) {
            System.out.println("username failed");
            return BaseResult.error("Enter your username");
        }
        if (password == null) {
            System.out.println("pwd failed");
            return BaseResult.error("Enter your password");
        }
        if (!password.equals(pwd)) {
            System.out.println("username unmatched");
            return BaseResult.error("Username or password code does not match");
        }

        /*loginLog.setSuccess(true);
        loginLog.setMessage("Successfully sign in");*/

        /*loginLogDao.insertLoginLog(loginLog);*/
        return BaseResult.ok("Successfully sign in");
    }

    @Override
    public BaseResult register(String username, String password, String avatar, String type) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setType(type);
        if (username == "") {
            return BaseResult.error("Username cannot be empty");
        } else if (password == "") {
            return BaseResult.error("Password cannot be empty");
        }
        User user4Check = userDao.findUserByUsername(username);
        if (user4Check != null) {
            return BaseResult.error("This username had been used");
        }

        userDao.saveUser(user);
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Successfully registered");
        return ok;
    }

    @Override
    public BaseResult deleteUserById(String id) {
        userDao.removeUserById(id);
        if (id == null) {
            BaseResult error = BaseResult.error();
            error.setMsg("Failed deleted, id cannot be empty");
            return error;
        }
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Successfully deleted");
        return ok;
    }

    @Override
    public BaseResult getUsers(int page, int rows) {
        List<User> usersList = userDao.findUsers(page, rows);
        BaseResult ok = new BaseResult();
        ok.setCnt(userDao.countUser());
        ok.setData(usersList);
        return ok;
    }

    @Override
    public BaseResult getUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return BaseResult.ok(user);
    }

    @Override
    public BaseResult getUserById(String id) {
        User user = userDao.findUserById(id);
        return BaseResult.ok(user);
    }

}
