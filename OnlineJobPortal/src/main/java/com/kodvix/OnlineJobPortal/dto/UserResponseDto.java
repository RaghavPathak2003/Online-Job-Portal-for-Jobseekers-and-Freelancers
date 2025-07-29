package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import com.kodvix.OnlineJobPortal.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private Gender gender;
    private LocalDate dob;

    private String profileImageUrl;
    private String resumeUrl;

    private Role role;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    // JobSeeker / Freelancer fields
    private String portfolioLink;
    private String currentCtc;
    private String expectedCtc;
    private String profileSummary;
    private String noticePeriod;
    private String certifications;
    private String preferredLocation;
    private String preferredDomain;

    // Related Entities using your custom DTO names
    private List<AddressDto> addresses;
    private List<SkillDto> skills;
    private List<ExperienceDto> experiences;
    private List<EducationDto> educations;
//    private CompanyDto company;
//    private List<JobDto> jobs;
//    private List<ProjectDto> projects;
    private List<LanguageDto> languages;
}
