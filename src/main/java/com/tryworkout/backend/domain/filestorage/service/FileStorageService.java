package com.tryworkout.backend.domain.filestorage.service;

import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;
import com.tryworkout.backend.domain.filestorage.dto.ImageCreateRequestDto;
import com.tryworkout.backend.domain.filestorage.service.strategy.EntityCreationStrategy;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final TrainerJpaRepository trainerJpaRepository;
    private final Map<Class<?>, EntityCreationStrategy<?>> creationStrategies;
    private final ModelMapper mapper;

    private void init(Path rootLocation) {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    public <T, V> V createImage(Path fileSavedPath, ImageCreateRequestDto imageCreateRequestDto, Class<T> entityClass, Class<V> dtoClass){
        init(fileSavedPath);
        String storedPath = store(imageCreateRequestDto.getFile(), fileSavedPath);
        Trainer trainer = trainerJpaRepository.findById(imageCreateRequestDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));

        ImageCreateDto<Trainer> dto = ImageCreateDto.<Trainer>builder()
                .entity(trainer)
                .url(storedPath)
                .explanation(imageCreateRequestDto.getExplanation())
                .build();

        EntityCreationStrategy<T> strategy = (EntityCreationStrategy<T>) creationStrategies.get(entityClass);
        if (strategy == null) {
            throw new BusinessException(ErrorCode.METHOD_NOT_ALLOWED);
        }

        T createdEntity = strategy.createEntity(dto);
        V result = mapper.map(createdEntity, dtoClass);

        return result;
    }

    private String store(MultipartFile file, Path rootLocation) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // Security check
                throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
            }
            Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }
}
