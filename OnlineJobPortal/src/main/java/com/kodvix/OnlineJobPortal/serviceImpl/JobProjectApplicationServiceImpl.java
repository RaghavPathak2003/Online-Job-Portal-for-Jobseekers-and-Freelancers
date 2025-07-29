package com.kodvix.OnlineJobPortal.serviceImpl;

import com.kodvix.OnlineJobPortal.dto.*;
import com.kodvix.OnlineJobPortal.entity.JobProjectApplication;
import com.kodvix.OnlineJobPortal.entity.JobProjectPost;
import com.kodvix.OnlineJobPortal.entity.User;
import com.kodvix.OnlineJobPortal.enums.ApplicationStatus;
import com.kodvix.OnlineJobPortal.exception.EntityNotFoundException;
import com.kodvix.OnlineJobPortal.repository.JobProjectApplicationRepository;
import com.kodvix.OnlineJobPortal.repository.JobProjectPostRepository;
import com.kodvix.OnlineJobPortal.repository.UserRepository;
import com.kodvix.OnlineJobPortal.service.JobProjectApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JobProjectApplicationServiceImpl implements JobProjectApplicationService {

    private final JobProjectApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobProjectPostRepository jobProjectPostRepository;
    private final ModelMapper modelMapper;
    @Override
    public JobSeekerApplicationResponseDto applyAsJobSeeker(JobSeekerApplicationRequestDto requestDto) {
        User applicant = userRepository.findById(requestDto.getApplicantId())
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found"));

        JobProjectPost jobProjectPost = jobProjectPostRepository.findById(requestDto.getJobProjectPostId())
                .orElseThrow(() -> new EntityNotFoundException("Job/Project Post not found"));

        // Manual mapping from RequestDto to Entity
        JobProjectApplication application = new JobProjectApplication();
        application.setApplicant(applicant);
        application.setJobProjectPost(jobProjectPost);
        application.setCoverLetter(requestDto.getCoverLetter());
        application.setCommunicationPreference(requestDto.getCommunicationPreference());
        application.setAppliedDate(LocalDateTime.now());
        application.setStatus(ApplicationStatus.APPLIED);

        application = applicationRepository.save(application);

        // Manual mapping from Entity to ResponseDto
        JobSeekerApplicationResponseDto responseDto = new JobSeekerApplicationResponseDto();
        responseDto.setApplicationId(application.getId());
        responseDto.setApplicantId(applicant.getId());
        responseDto.setJobProjectPostId(requestDto.getJobProjectPostId());
        responseDto.setApplicantName(applicant.getFirstName() + " " + applicant.getLastName());
        responseDto.setCoverLetter(application.getCoverLetter());
        responseDto.setCommunicationPreference(application.getCommunicationPreference());
        responseDto.setAppliedDate(application.getAppliedDate());
        responseDto.setStatus(application.getStatus());

        return responseDto;
    }


    @Override
    public FreelancerApplicationResponseDto applyAsFreelancer(FreelancerApplicationRequestDto requestDto) {
        User applicant = userRepository.findById(requestDto.getApplicantId())
               .orElseThrow(() -> new EntityNotFoundException("Applicant not found"));

        JobProjectPost jobProjectPost=jobProjectPostRepository.findById(requestDto.getJobProjectPostId())
                .orElseThrow(() -> new EntityNotFoundException("Job is Not Foud"));

        JobProjectApplication application = new JobProjectApplication();
        application.setApplicant(applicant);
        application.setAppliedDate(LocalDateTime.now());
        application.setJobProjectPost(jobProjectPost);
        application.setCoverLetter(requestDto.getCoverLetter());
        application.setCommunicationPreference(requestDto.getCommunicationPreference());
        application.setDeliveryTimeframe(requestDto.getDeliveryTimeframe());
        application.setProjectFee(requestDto.getProjectFee());
        application.setProposedMilestones(requestDto.getProposedMilestones());
        application.setStatus(ApplicationStatus.APPLIED);

        application = applicationRepository.save(application);

        FreelancerApplicationResponseDto responseDto = new FreelancerApplicationResponseDto();
        responseDto.setApplicationId(application.getId());
        responseDto.setApplicantId(applicant.getId());
        responseDto.setJobProjectPostId(jobProjectPost.getId());
        responseDto.setApplicantName(applicant.getFirstName() + " " + applicant.getLastName());
        responseDto.setCoverLetter(application.getCoverLetter());
        responseDto.setCommunicationPreference(application.getCommunicationPreference());
        responseDto.setDeliveryTimeframe(application.getDeliveryTimeframe());
        responseDto.setProjectFee(application.getProjectFee());
        responseDto.setProposedMilestones(application.getProposedMilestones());
        responseDto.setStatus(application.getStatus());
        responseDto.setAppliedDate(application.getAppliedDate());

        return responseDto;
    }

//    @Override
//    public void updateApplicationFeedbackAndStatus(Long applicationId, String feedback, String statusStr) {
//        JobProjectApplication application = applicationRepository.findById(applicationId).get();
//          //      .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
//
//        ApplicationStatus status;
//        try {
//            status = ApplicationStatus.valueOf(statusStr.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Invalid application status: " + statusStr);
//        }
//
//        application.setStatus(status);
//        application.setRecruiterFeedback(feedback);
//
//        applicationRepository.save(application);
//    }
}
