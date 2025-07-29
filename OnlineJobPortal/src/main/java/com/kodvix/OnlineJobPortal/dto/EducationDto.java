package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {

    //private int id;

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Institute name is required")
    private String institute;

    @NotBlank(message = "Passing year is required")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Invalid passing year format")
    private String passingYear;

    @DecimalMin(value = "0.0", inclusive = true, message = "Percentage must be >= 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Percentage must be <= 100")
    private double percentage;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "University name is required")
    private String university;

    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    // 10th details
    @NotBlank(message = "10th board is required")
    private String tenthBoard;

    @NotBlank(message = "10th school name is required")
    private String tenthSchoolName;

    @NotBlank(message = "10th passing year is required")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Invalid 10th passing year")
    private String tenthPassingYear;

    @DecimalMin(value = "0.0", message = "10th percentage must be >= 0")
    @DecimalMax(value = "100.0", message = "10th percentage must be <= 100")
    private double tenthPercentage;

    // 12th details
    @NotBlank(message = "12th board is required")
    private String twelfthBoard;

    @NotBlank(message = "12th school name is required")
    private String twelfthSchoolName;

    @NotBlank(message = "12th passing year is required")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Invalid 12th passing year")
    private String twelfthPassingYear;

    @DecimalMin(value = "0.0", message = "12th percentage must be >= 0")
    @DecimalMax(value = "100.0", message = "12th percentage must be <= 100")
    private double twelfthPercentage;

}
