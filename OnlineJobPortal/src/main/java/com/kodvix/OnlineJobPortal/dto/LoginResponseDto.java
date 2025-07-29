package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Role;
import lombok.Data;

@Data
public class LoginResponseDto {
    private Long userId;
    private String fullName;
    private Role role;
    private String message;
}
