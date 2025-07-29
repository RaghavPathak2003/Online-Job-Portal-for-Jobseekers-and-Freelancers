package com.kodvix.OnlineJobPortal.service;

import com.kodvix.OnlineJobPortal.dto.AdminRequestDto;
import com.kodvix.OnlineJobPortal.dto.AdminResponseDto;

public interface AdminService {
    AdminResponseDto createAdmin(AdminRequestDto dto);
    AdminResponseDto getAdminById(Long id);
}
