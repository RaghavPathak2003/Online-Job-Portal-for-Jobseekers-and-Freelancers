package com.kodvix.OnlineJobPortal.service;

import com.kodvix.OnlineJobPortal.dto.JobProjectPostRequestDto;
import com.kodvix.OnlineJobPortal.dto.JobProjectPostResponseDto;

import java.util.List;

public interface JobProjectPostService {

    JobProjectPostResponseDto createPost(JobProjectPostRequestDto dto);

    JobProjectPostResponseDto getPostById(Long id);

    List<JobProjectPostResponseDto> getAllPosts();

    JobProjectPostResponseDto updatePost(Long id, JobProjectPostRequestDto dto);

    List<JobProjectPostResponseDto> getAllJobsByRecruiter(Long recruiterId);

    void deletePost(Long id);

}
