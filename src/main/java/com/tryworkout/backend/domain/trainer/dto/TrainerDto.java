package com.tryworkout.backend.domain.trainer.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private Long id;
    private Long userId;
    private String introduction;
    private Long gymId;
}
