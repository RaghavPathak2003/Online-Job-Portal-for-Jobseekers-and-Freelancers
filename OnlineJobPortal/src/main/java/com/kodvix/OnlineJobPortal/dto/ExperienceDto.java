package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Position is required")
    private String position;

    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "CTC must be a valid number")
    private String ctc;

    private String noticePeriod;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Employment type is required")
    private String employmentType;

    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Years of experience must be a valid number")
    private String yearsOfExperience;

    private String industry;

    private String department;

    @NotBlank(message = "Skills are required")
    private String skills;

}
