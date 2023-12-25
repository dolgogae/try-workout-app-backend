package com.tryworkout.backend.domain.filestorage.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerImageResponse {

    private Long trainerId;
    private String url;
}
