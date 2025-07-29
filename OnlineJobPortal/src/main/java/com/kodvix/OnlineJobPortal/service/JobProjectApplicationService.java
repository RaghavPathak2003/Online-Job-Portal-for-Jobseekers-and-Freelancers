package com.kodvix.OnlineJobPortal.service;

import com.kodvix.OnlineJobPortal.dto.*;

public interface JobProjectApplicationService {

    // For JobSeeker
    JobSeekerApplicationResponseDto applyAsJobSeeker(JobSeekerApplicationRequestDto requestDto);

    // For Freelancer
    FreelancerApplicationResponseDto applyAsFreelancer(FreelancerApplicationRequestDto requestDto);

    // Update feedback and status by recruiter
//    void updateApplicationFeedbackAndStatus(Long applicationId, String feedback, String status);
}
