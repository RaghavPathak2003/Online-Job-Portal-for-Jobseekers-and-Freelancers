package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadResumeRequestDto {
    @NotNull(message = "Resume file must not be null")
    private MultipartFile resume;
}
