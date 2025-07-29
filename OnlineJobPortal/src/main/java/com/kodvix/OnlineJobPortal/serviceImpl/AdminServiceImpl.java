package com.kodvix.OnlineJobPortal.serviceImpl;
import com.kodvix.OnlineJobPortal.dto.AdminRequestDto;
import com.kodvix.OnlineJobPortal.dto.AdminResponseDto;
import com.kodvix.OnlineJobPortal.entity.User;
import com.kodvix.OnlineJobPortal.enums.Role;
import com.kodvix.OnlineJobPortal.exception.ResourceNotFoundException;
import com.kodvix.OnlineJobPortal.repository.UserRepository;
import com.kodvix.OnlineJobPortal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setDob(dto.getDob());
        user.setGender(dto.getGender());
        user.setPassword(dto.getPassword());
        user.setRole(Role.ADMIN);
//        user.setActive(true);

        User saved = userRepository.save(user);
        return mapToDto(saved);
    }

    @Override
    public AdminResponseDto getAdminById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "ID", id));

        if (user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("User is not an Admin");
        }

        return mapToDto(user);
    }

    private AdminResponseDto mapToDto(User user) {
        AdminResponseDto dto = new AdminResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setDob(user.getDob());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());
        return dto;
    }
}
