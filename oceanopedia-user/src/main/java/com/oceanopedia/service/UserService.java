package com.oceanopedia.service;


import com.oceanopedia.vo.BaseResult;

public interface UserService {

    BaseResult login(String username, String password, String phone, String verificationCode);

    BaseResult register(String username, String password, String avatar, String type);

    BaseResult deleteUserById(String id);

    BaseResult getUsers(int page, int rows);

    BaseResult getUserByUsername(String username);

    BaseResult getUserById(String id);

}
