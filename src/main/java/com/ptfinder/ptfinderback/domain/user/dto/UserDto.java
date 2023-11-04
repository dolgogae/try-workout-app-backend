package com.ptfinder.ptfinderback.domain.user.dto;

import lombok.*;
@Data
@Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
