package com.kodvix.OnlineJobPortal.service;

import com.kodvix.OnlineJobPortal.dto.FreelancerRequestDto;
import com.kodvix.OnlineJobPortal.dto.FreelancerResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FreelancerService {

    FreelancerResponseDto save(FreelancerRequestDto freelancerRequestDto);

    FreelancerResponseDto getById(Long id);

    List<FreelancerResponseDto> getAll();

    void deleteById(Long id);

    void updateById(Long id, FreelancerRequestDto freelancerRequestDto);

    void uploadUserImage(Long userId, MultipartFile image);

    byte[] getUserProfileImageById(Long id);

    void uploadUserResume(Long userId, MultipartFile resume);

    byte[] getUserResumeById(Long id);

}
