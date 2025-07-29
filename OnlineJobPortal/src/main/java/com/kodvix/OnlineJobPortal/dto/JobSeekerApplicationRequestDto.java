package com.kodvix.OnlineJobPortal.dto;

import lombok.Data;

@Data
public class JobSeekerApplicationRequestDto {
    private Long applicantId;
    private Long jobProjectPostId;
    private String coverLetter;
    private String communicationPreference; // email, chat, call
}
