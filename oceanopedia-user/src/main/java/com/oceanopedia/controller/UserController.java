package com.oceanopedia.controller;


import com.oceanopedia.service.UserService;
import com.oceanopedia.vo.BaseResult;
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
    public BaseResult register(String username, String password, String avatar, String type) {
        return userService.register(username, password, avatar, type);
    }

    @PostMapping("/login")
    public BaseResult login(String username, String password, String phone, String verificationCode) {
        return userService.login(username, password, phone, verificationCode);
    }

    @GetMapping("/getUsers")
    public BaseResult getUsers(int page, @RequestParam(defaultValue = "8") int rows) {
        return userService.getUsers(page, rows);
    }

    @PostMapping("/deleteUserById")
    public BaseResult deleteUserById(String id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/getLoggedInUser")
    public UserDetails getLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping("/getUserByUsername")
    public BaseResult getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserById")
    public BaseResult getUserById(String id) {
        return userService.getUserById(id);
    }
}
