package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerResponseDto {

    private String fullName;
    private String email;
    private String phone;
    private String portfolioLink;
    private String currentCtc;
    private String expectedCtc;
    private String profileSummary;
    private String noticePeriod;

    private String preferredLocation;
    private String preferredDomain;

    private List<CertificationDto> certifications;
    private List<SkillDto> skills;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;
    private List<LanguageDto> languages;
    private List<AddressDto> addresses;
}
