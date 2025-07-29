package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.entity.Address;
import com.kodvix.OnlineJobPortal.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JobSeekerRequestDto {
    // Optional: If you want to allow setting basic info during registration
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    private LocalDate dob;
    private LocalDate createdAt;
   private  LocalDate updatedAt;
    // JobSeeker-specific fields
    private String portfolioLink;
    private String currentCtc;
    private String expectedCtc;
    private String profileSummary;
    private String noticePeriod;
    private List<CertificationDto> certifications;
    private String preferredLocation;
    private String preferredDomain;

    // Nested List DTOs
    private List<SkillDto> skills;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;
    private List<LanguageDto> languages;
    private List<AddressDto>addresses;
}
