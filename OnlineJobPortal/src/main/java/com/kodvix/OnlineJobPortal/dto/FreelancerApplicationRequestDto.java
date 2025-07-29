package com.kodvix.OnlineJobPortal.dto;

import lombok.Data;

@Data
public class FreelancerApplicationRequestDto {
    private Long applicantId;
    private Long jobProjectPostId;
    private String coverLetter;
    private Double projectFee;
    private String deliveryTimeframe;
    private String proposedMilestones;
    private String communicationPreference; // email, chat, call
}
