package com.kodvix.OnlineJobPortal.serviceImpl;

import com.kodvix.OnlineJobPortal.dto.LoginRequestDto;
import com.kodvix.OnlineJobPortal.dto.LoginResponseDto;
import com.kodvix.OnlineJobPortal.entity.User;
import com.kodvix.OnlineJobPortal.exception.ResourceNotFoundException;
import com.kodvix.OnlineJobPortal.repository.UserRepository;
import com.kodvix.OnlineJobPortal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Login logic
    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid", "Credentials", dto.getEmail()));

//        if (!user.isActive()) {
//            throw new IllegalStateException("User is not active. Please contact admin.");
//        }

        LoginResponseDto response = new LoginResponseDto();
        response.setUserId(user.getId());
        response.setFullName(user.getFirstName() + " " + user.getLastName());
        response.setRole(user.getRole());
        response.setMessage("Login successful as " + user.getRole().name());

        return response;
    }







}
