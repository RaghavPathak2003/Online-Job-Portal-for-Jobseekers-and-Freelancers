package com.kodvix.OnlineJobPortal.controller;

//import com.kodvix.OnlineJobPortal.dto.RecruiterDto;
import com.kodvix.OnlineJobPortal.dto.*;
import com.kodvix.OnlineJobPortal.service.JobProjectPostService;
import com.kodvix.OnlineJobPortal.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private JobProjectPostService jobProjectPostService;

    @PostMapping("/save")
    public ResponseEntity<RecruiterResponseDto> save(@Valid @RequestBody RecruiterRequestDto recruiterRequestDto) {
        RecruiterResponseDto saved = recruiterService.save(recruiterRequestDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RecruiterResponseDto> getById(@PathVariable Long id) {
        RecruiterResponseDto recruiter = recruiterService.getById(id);
        return ResponseEntity.ok(recruiter);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RecruiterResponseDto>> getAll() {
        return ResponseEntity.ok(recruiterService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        recruiterService.deleteById(id);
        return ResponseEntity.ok("Recruiter Deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody RecruiterRequestDto recruiterRequestDto) {
        recruiterService.updateById(id, recruiterRequestDto);
        return ResponseEntity.ok("Recruiter Updated Successfully");
    }

    @GetMapping("/jobs/{id}")
    public List<JobProjectPostResponseDto> getJobsByRecruiter(@PathVariable Long recruiterId) {
        return jobProjectPostService.getAllJobsByRecruiter(recruiterId);
    }

    @PostMapping("/upload-image/{id}")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @ModelAttribute @Valid UploadImageRequestDto requestDto) {

        recruiterService.uploadUserImage(id, requestDto.getImage());
        return ResponseEntity.ok("Profile image uploaded successfully");
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long id) {
        byte[] imageData = recruiterService.getUserProfileImageById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // or IMAGE_JPEG based on actual image
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
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
