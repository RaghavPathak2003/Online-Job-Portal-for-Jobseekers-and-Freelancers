package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDto {

    @NotBlank(message = "Certification name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Issuing organization is required")
    @Size(max = 100, message = "Issuing organization must not exceed 100 characters")
    private String issuingOrganization;

    @PastOrPresent(message = "Issue date must be in the past or present")
    private LocalDate issueDate;

    private LocalDate expiryDate; // Optional, can be null

    private String credentialId;

    @URL(message = "Invalid credential URL")
    private String credentialUrl;
}
