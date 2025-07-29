package com.kodvix.OnlineJobPortal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageRequestDto {
    @NotNull(message = "Image file must not be null")
    private MultipartFile image;
}
