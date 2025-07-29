package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import com.kodvix.OnlineJobPortal.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;
    private Gender gender;
    private Role role;
}
