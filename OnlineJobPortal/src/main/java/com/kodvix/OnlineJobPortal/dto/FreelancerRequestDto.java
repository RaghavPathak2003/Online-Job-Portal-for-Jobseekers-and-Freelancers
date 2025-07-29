package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerRequestDto {

        @NotNull(message = "First name is required")
        private String firstName;
        @NotNull(message = "Last name is required")
        private String lastName;
        @NotNull(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;
        @NotNull(message = "Phone is required")
        //@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
        private String phone;
        @NotNull(message = "Gender is required")
        private Gender gender;
        @Past(message = "Date of birth must be in the past")
        private LocalDate dob;
        private String profileImage;

        @NotNull(message = "Resume cannot be null")
        private String uploadResume;

        @Valid
        private List<AddressDto> address;
        @Valid
        private List<CertificationDto> certifications;
        @Valid
        private List<LanguageDto> languages;
        @Valid
        private List<SkillDto> skills;
        @Valid
        private List<EducationDto> educations;
        @Valid
        private List<ExperienceDto> experiences;

        private String preferredDomain;
        @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Expected hourly rate must be a valid number")
        private String expectedHourlyPayRate;
        @NotNull(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        private String password;
        @Size(max = 500, message = "Profile summary should not exceed 500 characters")
        private String profileSummary;
        @URL(message = "Invalid portfolio link")
        private String portfolioLink;
        private String availabilityToJoin;
        private String preferredLocation;

}
