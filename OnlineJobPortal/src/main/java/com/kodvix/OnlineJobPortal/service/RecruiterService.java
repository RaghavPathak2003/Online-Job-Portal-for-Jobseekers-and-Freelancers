package com.kodvix.OnlineJobPortal.service;

//import com.kodvix.OnlineJobPortal.dto.RecruiterDto;
import com.kodvix.OnlineJobPortal.dto.RecruiterRequestDto;
import com.kodvix.OnlineJobPortal.dto.RecruiterResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecruiterService {

    RecruiterResponseDto save(RecruiterRequestDto recruiterRequestDto);

    RecruiterResponseDto getById(Long id);

    List<RecruiterResponseDto> getAll();

    void deleteById(Long id);

    void updateById(Long id, RecruiterRequestDto recruiterRequestDto);

    void uploadUserImage(Long userId, MultipartFile image);

    byte[] getUserProfileImageById(Long id);

}
