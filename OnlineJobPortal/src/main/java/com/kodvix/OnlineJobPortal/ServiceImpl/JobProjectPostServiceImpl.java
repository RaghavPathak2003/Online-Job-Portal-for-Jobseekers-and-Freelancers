package com.kodvix.OnlineJobPortal.ServiceImpl;

import com.kodvix.OnlineJobPortal.dto.JobProjectPostRequestDto;
import com.kodvix.OnlineJobPortal.dto.JobProjectPostResponseDto;
import com.kodvix.OnlineJobPortal.entity.JobProjectPost;
import com.kodvix.OnlineJobPortal.entity.User;
import com.kodvix.OnlineJobPortal.enums.Role;
import com.kodvix.OnlineJobPortal.exception.ResourceNotFoundException;
import com.kodvix.OnlineJobPortal.repository.JobProjectPostRepository;
import com.kodvix.OnlineJobPortal.repository.UserRepository;
import com.kodvix.OnlineJobPortal.service.JobProjectPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobProjectPostServiceImpl implements JobProjectPostService {

    @Autowired
    JobProjectPostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public JobProjectPostResponseDto createPost(JobProjectPostRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", dto.getUserId()));

        if (user.getRole() != Role.RECRUITER) {
            throw new RuntimeException("Only recruiters are allowed to post jobs/projects.");
        }

        JobProjectPost post = modelMapper.map(dto, JobProjectPost.class);

        // Ensure ID is null
        post.setId(null);

        post.setUser(user);
        post.setPostedDate(LocalDate.now());

        JobProjectPost saved = postRepository.save(post);
        return modelMapper.map(saved, JobProjectPostResponseDto.class);
    }

    @Override
    public JobProjectPostResponseDto getPostById(Long id) {
        JobProjectPost post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
        return modelMapper.map(post, JobProjectPostResponseDto.class);
    }

    @Override
    public List<JobProjectPostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, JobProjectPostResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<JobProjectPostResponseDto> getAllJobsByRecruiter(Long recruiterId) {
        List<JobProjectPost> jobProjects = postRepository.findByUserId(recruiterId);
        return jobProjects.stream().map(this::convertToDto).collect(Collectors.toList());

    }

        private JobProjectPostResponseDto convertToDto(JobProjectPost jobProjectPost) {
            JobProjectPostResponseDto dto = new JobProjectPostResponseDto();
            dto.setId(jobProjectPost.getId());
            dto.setTitle(jobProjectPost.getTitle());
            dto.setDescription(jobProjectPost.getDescription());
            dto.setMandatorySkills(jobProjectPost.getMandatorySkills());
            dto.setQualifications(jobProjectPost.getQualifications());
            dto.setExperienceRequired(jobProjectPost.getExperienceRequired());
            dto.setPostFor(jobProjectPost.getPostFor());
            dto.setCtc(jobProjectPost.getCtc());
            dto.setHourlyPayRate(jobProjectPost.getHourlyPayRate());
            return dto;

    }


    @Override
    public JobProjectPostResponseDto updatePost(Long id, JobProjectPostRequestDto dto) {
        JobProjectPost post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));

        // Map only the safe fields manually (not ID or user)
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setPostFor(dto.getPostFor());
        post.setMandatorySkills(dto.getMandatorySkills());
        post.setQualifications(dto.getQualifications());
        post.setExperienceRequired(dto.getExperienceRequired());

        post.setCompanyName(dto.getCompanyName());
        post.setCtc(dto.getCtc());
        post.setJobLocation(dto.getJobLocation());
        post.setEmploymentType(dto.getEmploymentType());
        post.setAboutCompany(dto.getAboutCompany());
        post.setWorkMode(dto.getWorkMode());

        post.setClientName(dto.getClientName());
        post.setHourlyPayRate(dto.getHourlyPayRate());
        post.setAboutClient(dto.getAboutClient());
        post.setProjectDuration(dto.getProjectDuration());

        post.setPostedDate(LocalDate.now()); // update post date

        JobProjectPost updated = postRepository.save(post);
        return modelMapper.map(updated, JobProjectPostResponseDto.class);
    }

    @Override
    public void deletePost(Long id) {
        JobProjectPost post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
        postRepository.delete(post);
    }
}
