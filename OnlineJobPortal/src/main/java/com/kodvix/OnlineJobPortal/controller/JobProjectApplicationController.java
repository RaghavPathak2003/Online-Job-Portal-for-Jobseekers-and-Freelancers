package com.kodvix.OnlineJobPortal.controller;

import com.kodvix.OnlineJobPortal.dto.*;
import com.kodvix.OnlineJobPortal.service.JobProjectApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobProjectApplicationController {

    private final JobProjectApplicationService applicationService;

    // ---------------------- Apply as JobSeeker ----------------------
        @PostMapping("/jobseeker/apply")
    public ResponseEntity<JobSeekerApplicationResponseDto> applyAsJobSeeker(
            @RequestBody JobSeekerApplicationRequestDto requestDto) {
        JobSeekerApplicationResponseDto responseDto = applicationService.applyAsJobSeeker(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // ---------------------- Apply as Freelancer ----------------------
    @PostMapping("/freelancer/apply")
    public ResponseEntity<FreelancerApplicationResponseDto> applyAsFreelancer(
            @RequestBody FreelancerApplicationRequestDto requestDto) {
        FreelancerApplicationResponseDto responseDto = applicationService.applyAsFreelancer(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // ---------------------- Update Feedback and Status by Recruiter ----------------------
//    @PutMapping("/{applicationId}/feedback-status")
//    public ResponseEntity<String> updateFeedbackAndStatus(
//            @PathVariable Long applicationId,
//            @RequestParam String feedback,
//            @RequestParam String status) {
//        applicationService.updateApplicationFeedbackAndStatus(applicationId, feedback, status);
//        return ResponseEntity.ok("Application status and feedback updated successfully.");
//    }
}
