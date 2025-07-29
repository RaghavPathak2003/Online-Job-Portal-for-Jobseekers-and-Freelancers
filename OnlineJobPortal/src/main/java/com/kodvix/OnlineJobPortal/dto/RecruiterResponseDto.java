package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RecruiterResponseDto {

    private Long userId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate dob;
    private String profileImage;

    private String companyName;
    private String designation;
    private String website;
    private String description;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
