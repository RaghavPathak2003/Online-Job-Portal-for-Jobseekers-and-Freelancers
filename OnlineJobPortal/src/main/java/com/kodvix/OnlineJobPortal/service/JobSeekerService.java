package com.kodvix.OnlineJobPortal.service;

import com.kodvix.OnlineJobPortal.dto.JobSeekerRequestDto;
import com.kodvix.OnlineJobPortal.dto.JobSeekerResponseDto;
import com.kodvix.OnlineJobPortal.dto.LoginRequestDto;
import com.kodvix.OnlineJobPortal.dto.LoginResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobSeekerService {
    JobSeekerResponseDto registerJobSeekerProfile(JobSeekerRequestDto dto);
    JobSeekerResponseDto getJobSeekerProfile(Long userId);
    List<JobSeekerResponseDto> getAllJobSeekers();
    JobSeekerResponseDto updateJobSeekerProfile(Long userId, JobSeekerRequestDto dto);
    void deleteJobSeeker(Long userId);
    void uploadUserImage(Long userId, MultipartFile imageFile);
    byte[] getUserProfileImageById(Long userId);
    void uploadUserResume(Long userId, MultipartFile resume);
    byte[] getUserResumeById(Long userId);




}
