package com.tryworkout.backend.domain.filestorage.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageCreateDto<T> {

    private T entity;
    private String url;
}
