package com.kodvix.OnlineJobPortal.dto;

import com.kodvix.OnlineJobPortal.enums.ApplicationStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobSeekerApplicationResponseDto {
    private Long applicationId;
    private Long applicantId;
    private String applicantName;
    private Long jobProjectPostId;
    private ApplicationStatus status;
    private LocalDateTime appliedDate;
    private String coverLetter;
    private String communicationPreference;
    //private String recruiterFeedback;
    //private Double applicantRating;
}
