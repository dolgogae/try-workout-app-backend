package com.tryworkout.backend.domain.filestorage.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageCreateRequestDto {

    private MultipartFile file;
    private String explanation;
    private Long id;
}
