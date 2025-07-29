package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RecruiterRequestDto {

    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotNull(message = "Phone is required")
    //@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String phone;
    @NotNull(message = "Gender is required")
    private Gender gender;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;
    private String profileImage;
    @Size(max = 500, message = "Profile summary should not exceed 500 characters")
    private String profileSummary;

    @NotNull(message = "Company name is required")
    private String companyName;
    @NotNull(message = "Designation is required")
    private String designation;
    @URL(message = "Invalid website URL")
    private String website;
    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;

}
