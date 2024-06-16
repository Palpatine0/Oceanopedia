package com.oceanopedia.service;


import com.oceanopedia.vo.OceanopediaResult;

public interface UserService {

    OceanopediaResult login(String username, String password, String phone, String verificationCode);

    OceanopediaResult register(String username, String password, String avatar, String type);

    OceanopediaResult deleteUserById(String id);

    OceanopediaResult getUsers();

    OceanopediaResult getUserByUsername(String username);

    OceanopediaResult getUserById(String id);

}
