package com.kodvix.OnlineJobPortal.controller;

import com.kodvix.OnlineJobPortal.dto.AdminRequestDto;
import com.kodvix.OnlineJobPortal.dto.AdminResponseDto;
import com.kodvix.OnlineJobPortal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public AdminResponseDto createAdmin(@RequestBody AdminRequestDto dto) {
        return adminService.createAdmin(dto);
    }

    @GetMapping("/{id}")
    public AdminResponseDto getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }
}
