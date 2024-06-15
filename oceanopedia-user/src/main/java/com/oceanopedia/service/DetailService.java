package com.oceanopedia.service;

import com.oceanopedia.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class DetailService implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.oceanopedia.entity.User user = userDao.findUserByUsername(username);
        String password = "";
        if (user == null) {
        } else {
            password = user.getPassword();
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        User userDetail = new User(
                username,
                "{noop}" + password,
                true,
                true,
                true,
                true,
                authorities);

        return userDetail;

    }
}
