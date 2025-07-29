package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerResponseDto {

        //private Long id; // assuming entity has an ID
        private Long userId;

        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private Gender gender;
        private LocalDate dob;
        //private String profileImage;
        //private String uploadResume;
        private List<LanguageDto> languages;

        private List<AddressDto> address;
        private List<SkillDto> skills;
        private List<EducationDto> educations;
        private List<ExperienceDto> experiences;
        private List<CertificationDto> certifications;

        private String preferredDomain;
        private String expectedHourlyPayRate;

        private String profileSummary;

       private String portfolioLink;
        //private String availabilityToJoin;
        private String preferredLocation;

       // private LocalDate createdAt;
        //private LocalDate updatedAt;

}
