package com.tryworkout.backend.domain.gym.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymOperatingTimeDto {
    @NotBlank
    private Long trainerId;
    @NotBlank
    private Long gymId;
    @NotBlank
    @Pattern(regexp = "^\\d{2}:\\d{2} ~ \\d{2}:\\d{2}$",
            message = "Time slot format must be 'HH:mm ~ HH:mm'")
    private String businessHoursWeek;
    @NotBlank
    @Pattern(regexp = "^\\d{2}:\\d{2} ~ \\d{2}:\\d{2}$",
            message = "Time slot format must be 'HH:mm ~ HH:mm'")
    private String businessHoursWeekend;
    private String holiday;
}
