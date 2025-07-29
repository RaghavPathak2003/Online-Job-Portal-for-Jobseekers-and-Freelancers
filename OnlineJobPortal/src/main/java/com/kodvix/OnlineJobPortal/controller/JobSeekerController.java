package com.kodvix.OnlineJobPortal.controller;

import com.kodvix.OnlineJobPortal.dto.JobSeekerRequestDto;
import com.kodvix.OnlineJobPortal.dto.JobSeekerResponseDto;
import com.kodvix.OnlineJobPortal.dto.UploadImageRequestDto;
import com.kodvix.OnlineJobPortal.dto.UploadResumeRequestDto;
import com.kodvix.OnlineJobPortal.service.JobSeekerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobseekers")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    // ✅ Register or update JobSeeker profile
    @PostMapping("/save")
    public ResponseEntity<JobSeekerResponseDto> registerJobSeeker(@RequestBody JobSeekerRequestDto dto) {
        JobSeekerResponseDto response = jobSeekerService.registerJobSeekerProfile(dto);
        return ResponseEntity.ok(response);
    }

    // ✅ Get JobSeeker profile by userId
    @GetMapping("/get/{id}")
    public ResponseEntity<JobSeekerResponseDto> getJobSeekerProfile(@PathVariable Long id) {
        JobSeekerResponseDto profile = jobSeekerService.getJobSeekerProfile(id);
        return ResponseEntity.ok(profile);
    }

    // Get all JobSeekers
    @GetMapping("/getAll")
    public ResponseEntity<List<JobSeekerResponseDto>> getAllJobSeekers() {
        List<JobSeekerResponseDto> allJobSeekers = jobSeekerService.getAllJobSeekers();
        return ResponseEntity.ok(allJobSeekers);
    }

    // Update JobSeeker profile
    @PutMapping("/update/{id}")
    public ResponseEntity<JobSeekerResponseDto> updateJobSeeker(@PathVariable Long id, @RequestBody JobSeekerRequestDto dto) {
        JobSeekerResponseDto updated = jobSeekerService.updateJobSeekerProfile(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete JobSeeker by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJobSeeker(@PathVariable Long id) {
        jobSeekerService.deleteJobSeeker(id);
        return ResponseEntity.ok("JobSeeker with ID " + id + " deleted successfully.");
    }

    @PostMapping("/upload-image/{id}")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @ModelAttribute @Valid UploadImageRequestDto requestDto) {

        jobSeekerService.uploadUserImage(id, requestDto.getImage());
        return ResponseEntity.ok("Profile image uploaded successfully");
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long id) {
        byte[] imageData = jobSeekerService.getUserProfileImageById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // or IMAGE_JPEG based on actual image
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @PostMapping("/upload-resume/{id}")
    public ResponseEntity<String> uploadResume(@PathVariable Long id, @ModelAttribute @Valid UploadResumeRequestDto requestDto) {

        jobSeekerService.uploadUserResume(id, requestDto.getResume());
        return ResponseEntity.ok("Resume uploaded successfully");
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> getUserResume(@PathVariable Long id) {
        byte[] resumeData = jobSeekerService.getUserResumeById(id);


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
