package com.ptfinder.ptfinderback.domain.trainer.dto;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private Long id;
    private UserEntity user;
    private Integer fee;
    private Float discountRate;
    private String introduction;
    private Gym gym;
}
