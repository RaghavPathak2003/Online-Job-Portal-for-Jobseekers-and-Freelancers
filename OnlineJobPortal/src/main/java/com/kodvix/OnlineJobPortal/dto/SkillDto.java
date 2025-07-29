package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {

    // int id;

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Proficiency is required")
    private String proficiency; // e.g., Beginner, Intermediate, Advanced

    @Min(value = 0, message = "Years of experience must be zero or positive")
    @Max(value = 50, message = "Years of experience must be realistic (max 50)")
    private int yearsOfExperience;

}
