package com.oceanopedia.controller;


import com.oceanopedia.service.UserService;
import com.oceanopedia.vo.OceanopediaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public OceanopediaResult register(String username, String password, String phone) {
        return userService.register(username, password, phone);
    }

    @PostMapping("/login")
    public OceanopediaResult login(String username, String password, String phone, String verificationCode) {
        return userService.login(username, password, phone, verificationCode);
    }

    @PostMapping("/deleteUserById")
    public OceanopediaResult deleteUserById(String id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/getUsers")
    public OceanopediaResult getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/getLoggedInUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping("/getUserByUsername")
    public OceanopediaResult getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserById")
    public OceanopediaResult getUserById(String id) {
        return userService.getUserById(id);
    }
}
