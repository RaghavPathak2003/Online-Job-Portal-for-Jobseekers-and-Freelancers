package com.kodvix.OnlineJobPortal.controller;

import com.kodvix.OnlineJobPortal.dto.LoginRequestDto;
import com.kodvix.OnlineJobPortal.dto.LoginResponseDto;

import com.kodvix.OnlineJobPortal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 🔐 Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = userService.login(loginRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






}
