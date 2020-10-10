package com.tw.practise.controller;

import com.tw.practise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestParam String userName, @RequestParam String password, @RequestParam String telephone) {
        long userId = userService.register(userName, password, telephone);

        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

}
