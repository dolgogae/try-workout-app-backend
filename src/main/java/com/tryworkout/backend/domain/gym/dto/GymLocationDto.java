package com.tryworkout.backend.domain.gym.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymLocationDto implements Comparable<GymLocationDto> {

    private Long id;
    private String gymName;
    private Float mapX;
    private Float mapY;
    private Float distance;

    public void calculateDistance(GymLocationDto gymLocationDto) {
        distance = (float) Math.sqrt(
                Math.pow(mapX - gymLocationDto.getMapX(), 2) + Math.pow(mapY - gymLocationDto.getMapY(), 2));
    }

    @Override
    public int compareTo(GymLocationDto other) {
        return Float.compare(this.distance, other.distance);
    }
}
