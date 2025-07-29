package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dob;
    private Gender gender;
}
