package com.hydu.controller;

import com.hydu.service.UserService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;

/**
 * Created by heyong
 * 2019/7/30
 */

@CrossOrigin
@RestController(value = "/user")
public class UserCotroller {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;



}
