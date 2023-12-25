package com.tryworkout.backend.domain.filestorage.service;

import com.tryworkout.backend.domain.filestorage.data.TrainerImage;
import com.tryworkout.backend.domain.filestorage.dto.ImageCreateRequestDto;
import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;
import com.tryworkout.backend.domain.filestorage.dto.TrainerImageResponse;
import com.tryworkout.backend.domain.filestorage.repository.TrainerImageJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final Path rootLocation = Paths.get("/tmp/images");
    private final TrainerJpaRepository trainerJpaRepository;
    private final TrainerImageJpaRepository trainerImageJpaRepository;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    public TrainerImageResponse createTrainerImage(ImageCreateRequestDto imageCreateRequestDto){
        String filePath = store(imageCreateRequestDto.getFile());
        Trainer trainer = trainerJpaRepository.findById(imageCreateRequestDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));

        ImageCreateDto<Trainer> dto = ImageCreateDto.<Trainer>builder()
                .entity(trainer)
                .url(filePath)
                .build();

        TrainerImage trainerImage = TrainerImage.create(dto);
        TrainerImage savedTrainerImage = trainerImageJpaRepository.save(trainerImage);

        TrainerImageResponse result = TrainerImageResponse.builder()
                .trainerId(savedTrainerImage.getTrainer().getId())
                .url(savedTrainerImage.getUrl())
                .build();

        return result;
    }

    private String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // Security check
                throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }
}
