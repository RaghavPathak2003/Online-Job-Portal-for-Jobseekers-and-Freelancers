package com.kodvix.OnlineJobPortal.service;

import com.kodvix.OnlineJobPortal.dto.LoginRequestDto;
import com.kodvix.OnlineJobPortal.dto.LoginResponseDto;
import com.kodvix.OnlineJobPortal.entity.User;

import java.util.List;

public interface UserService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);


}
