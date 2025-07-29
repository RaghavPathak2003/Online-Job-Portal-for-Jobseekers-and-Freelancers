package com.kodvix.OnlineJobPortal.controller;

//import com.kodvix.OnlineJobPortal.dto.FreelancerDto;
import com.kodvix.OnlineJobPortal.dto.FreelancerRequestDto;
import com.kodvix.OnlineJobPortal.dto.FreelancerResponseDto;
import com.kodvix.OnlineJobPortal.dto.UploadImageRequestDto;
import com.kodvix.OnlineJobPortal.dto.UploadResumeRequestDto;
import com.kodvix.OnlineJobPortal.service.FreelancerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancers")
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    @PostMapping("/save")
    public ResponseEntity<FreelancerResponseDto> save(@Valid @RequestBody FreelancerRequestDto freelancerRequestDto) {
        FreelancerResponseDto saved = freelancerService.save(freelancerRequestDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FreelancerResponseDto> get(@PathVariable Long id) {
        FreelancerResponseDto freelancer = freelancerService.getById(id);
        return ResponseEntity.ok(freelancer);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FreelancerResponseDto>> getAll() {
        return ResponseEntity.ok(freelancerService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        freelancerService.deleteById(id);
        return ResponseEntity.ok("Freelancer Deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody FreelancerRequestDto freelancerRequestDto) {
        freelancerService.updateById(id, freelancerRequestDto);
        return ResponseEntity.ok("Freelancer Updated Successfully");
    }

    @PostMapping("/upload-image/{id}")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @ModelAttribute @Valid UploadImageRequestDto requestDto) {

        freelancerService.uploadUserImage(id, requestDto.getImage());
        return ResponseEntity.ok("Profile image uploaded successfully");
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long id) {
        byte[] imageData = freelancerService.getUserProfileImageById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // or IMAGE_JPEG based on actual image
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @PostMapping("/upload-resume/{id}")
    public ResponseEntity<String> uploadResume(@PathVariable Long id, @ModelAttribute @Valid UploadResumeRequestDto requestDto) {

        freelancerService.uploadUserResume(id, requestDto.getResume());
        return ResponseEntity.ok("Resume uploaded successfully");
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> getUserResume(@PathVariable Long id) {
        byte[] resumeData = freelancerService.getUserResumeById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "resume.");

        return new ResponseEntity<>(resumeData, headers, HttpStatus.OK);
    }

    private String getExtensionFromContentType(String contentType) {
        switch (contentType) {
            case "application/pdf": return "pdf";
            case "application/msword": return "doc";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": return "docx";
            default: return "bin";
        }
    }

}