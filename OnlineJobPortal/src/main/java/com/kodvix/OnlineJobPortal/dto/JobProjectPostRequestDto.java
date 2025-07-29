package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JobProjectPostRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    // Common
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title should not exceed 100 characters")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;
    @NotBlank(message = "Post type is required")
    @Pattern(regexp = "JOBSEEKER|FREELANCER", message = "Post type must be 'JOBSEEKER' or 'FREELANCER'")
    private String postFor;
    @NotBlank(message = "Mandatory skills are required")
    private String mandatorySkills;
    @NotBlank(message = "Qualifications are required")
    private String qualifications;
    @NotBlank(message = "Experience required field is mandatory")
    private String experienceRequired;

    // Jobseeker specific
    //@NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String companyName;
    //@PositiveOrZero(message = "CTC must be zero or positive")
    private Double ctc;
    private String jobLocation;
    private String employmentType;
    private String aboutCompany;
    private String workMode;

    // Freelancer specific
    private String clientName;
    //@PositiveOrZero(message = "Hourly pay rate must be zero or positive")
    private Double hourlyPayRate;
    private String aboutClient;
    private String projectDuration;

}
