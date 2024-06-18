package com.oceanopedia.controller;


import com.oceanopedia.service.UserService;
import com.oceanopedia.vo.OceanopediaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public OceanopediaResult register(String username, String password, String avatar, String type) {
        return userService.register(username, password, avatar, type);
    }

    @PostMapping("/login")
    public OceanopediaResult login(String username, String password, String phone, String verificationCode) {
        return userService.login(username, password, phone, verificationCode);
    }

    @GetMapping("/getUsers")
    public OceanopediaResult getUsers(int page, @RequestParam(defaultValue = "8") int rows) {
        return userService.getUsers(page, rows);
    }

    @PostMapping("/deleteUserById")
    public OceanopediaResult deleteUserById(String id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/getLoggedInUser")
    public UserDetails getLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {
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
