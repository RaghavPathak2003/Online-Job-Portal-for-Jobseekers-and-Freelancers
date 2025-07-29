package com.kodvix.OnlineJobPortal.serviceImpl;

import com.kodvix.OnlineJobPortal.dto.RecruiterRequestDto;
import com.kodvix.OnlineJobPortal.dto.RecruiterResponseDto;
import com.kodvix.OnlineJobPortal.entity.Company;
import com.kodvix.OnlineJobPortal.entity.User;
import com.kodvix.OnlineJobPortal.enums.Role;
import com.kodvix.OnlineJobPortal.exception.ResourceNotFoundException;
import com.kodvix.OnlineJobPortal.repository.UserRepository;
import com.kodvix.OnlineJobPortal.service.RecruiterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
    public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecruiterResponseDto save(RecruiterRequestDto dto) {
        User user = modelMapper.map(dto, User.class);

        user.setRole(Role.RECRUITER);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        user.setDob(dto.getDob());
        user.setProfileSummary(dto.getProfileSummary());

        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setDesignation(dto.getDesignation());
        company.setDescription(dto.getDescription());
        company.setWebsite(dto.getWebsite());
        company.setUser(user);
        user.setCompany(company);

        User saved = userRepository.save(user);

        RecruiterResponseDto responseDto = modelMapper.map(saved, RecruiterResponseDto.class);
        if (saved.getCompany() != null) {
            responseDto.setCompanyName(saved.getCompany().getCompanyName());
            responseDto.setDesignation(saved.getCompany().getDesignation());
            responseDto.setWebsite(saved.getCompany().getWebsite());
            responseDto.setDescription(saved.getCompany().getDescription());
        }

        return responseDto;
    }

    @Override
    public RecruiterResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        if (user.getRole() != Role.RECRUITER) {
            throw new IllegalArgumentException("Only users with RECRUITER role can be get by this endpoint.");
        }

        RecruiterResponseDto dto = modelMapper.map(user, RecruiterResponseDto.class);

        // Manually map nested Company entity if needed
        if (user.getCompany() != null) {
            dto.setCompanyName(user.getCompany().getCompanyName());
            dto.setDesignation(user.getCompany().getDesignation());
            dto.setDescription(user.getCompany().getDescription());
            dto.setWebsite(user.getCompany().getWebsite());
        }

        return dto;
    }

    @Override
    public List<RecruiterResponseDto> getAll() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.RECRUITER)
                .map(user -> {
                    RecruiterResponseDto dto = modelMapper.map(user, RecruiterResponseDto.class);
                    if (user.getCompany() != null) {
                        dto.setCompanyName(user.getCompany().getCompanyName());
                        dto.setDesignation(user.getCompany().getDesignation());
                        dto.setDescription(user.getCompany().getDescription());
                        dto.setWebsite(user.getCompany().getWebsite());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        if (user.getRole() != Role.RECRUITER) {
            throw new IllegalArgumentException("Only users with RECRUITER role can be deleted by this endpoint.");
        }

        userRepository.delete(user);
    }

    @Override
    public void updateById(Long id, RecruiterRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        if (user.getRole() != Role.RECRUITER) {
            throw new IllegalArgumentException("Only users with RECRUITER role can be updated by this endpoint.");
        }

        modelMapper.map(dto, user); // maps User fields
        user.setUpdatedAt(LocalDate.now());

        // Update Company fields
        Company company = user.getCompany();

        company.setCompanyName(dto.getCompanyName());
        company.setDesignation(dto.getDesignation());
        company.setWebsite(dto.getWebsite());
        company.setDescription(dto.getDescription());

        userRepository.save(user);
    }


//    private RecruiterResponseDto mapToRecruiterResponseDto(User user) {
//        RecruiterResponseDto dto = new RecruiterResponseDto();
//
//        dto.setFirstName(user.getFirstName());
//        dto.setLastName(user.getLastName());
//        dto.setEmail(user.getEmail());
//        dto.setPhone(user.getPhone());
//        //dto.setPassword(user.getPassword());
//        dto.setGender(user.getGender());
//        dto.setDob(user.getDob());
//        dto.setProfileImage(user.getProfileImage());
//        dto.setCreatedAt(user.getCreatedAt());
//        dto.setUpdatedAt(user.getUpdatedAt());
//
//        if (user.getCompany() != null) {
//            dto.setCompanyName(user.getCompany().getCompanyName());
//            dto.setDesignation(user.getCompany().getDesignation());
//        }
//
//        return dto;
//     }

    @Override
    public void uploadUserImage(Long userId, MultipartFile image) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        try {
            user.setProfileImage(image.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }

    @Override
    public byte[] getUserProfileImageById(Long id) {
        User user = userRepository.findById(id).get();

        return user.getProfileImage(); // assuming this is a byte[]
    }

}
