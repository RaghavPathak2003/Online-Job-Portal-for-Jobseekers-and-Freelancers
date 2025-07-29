package com.kodvix.OnlineJobPortal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobProjectPostResponseDto {

    private Long id;
    private Long userId;

    //common
    private String title;
    private String description;
    private String postFor;
    private LocalDate postedDate;
    private String mandatorySkills;
    private String qualifications;
    private String experienceRequired;

    //Jobseeker specific
    private String companyName;
    private Double ctc;
    private String jobLocation;
    private String employmentType;
    private String aboutCompany;
    private String workMode;

    //Freelancer specific
    private String clientName;
    private Double hourlyPayRate;
    private String aboutClient;
    private String projectDuration;

}
