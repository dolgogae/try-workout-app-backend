package com.tryworkout.backend.unit.domain.filestorage.service;

import com.tryworkout.backend.domain.filestorage.dto.ImageCreateRequestDto;
import com.tryworkout.backend.domain.filestorage.service.FileStorageService;
import com.tryworkout.backend.domain.filestorage.service.strategy.EntityCreationStrategy;
import com.tryworkout.backend.domain.filestorage.service.strategy.TrainerImageCreationStrategy;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class FileStorageServiceTest {

    @Mock
    FileStorageService fileStorageService;
    @Mock
    TrainerJpaRepository trainerJpaRepository;
    @Mock
    Map<Class<?>, EntityCreationStrategy<?>> creationStrategies;
    @Mock
    EntityCreationStrategy<TrainerImageCreationStrategy> trainerStrategy;
    @Autowired
    ModelMapper modelMapper;


    @BeforeEach
    void before(){

    }

    @Test
    @DisplayName("이미지 생성 테스트")
    public void createImageTest(){
        // given
        fileStorageService = new FileStorageService(trainerJpaRepository, creationStrategies, modelMapper);
        MultipartFile file = getMockMultipartFile();
        ImageCreateRequestDto dto = ImageCreateRequestDto.builder()
                .id(1L)
                .explanation("설명")
                .file(file)
                .build();
        Trainer trainer = new Trainer();
        ReflectionTestUtils.setField(trainer, "id", 1L);

        when(trainerJpaRepository.findById(1L))
                .thenReturn(Optional.of(trainer));
//        when(creationStrategies.get(TrainerImageCreationStrategy.class))
//                .thenReturn(trainerStrategy);

        // when

        // then
    }

    private static MockMultipartFile getMockMultipartFile() {
        return new MockMultipartFile(
                "홍길동전 썸네일 이미지",
                "thumbnail.png",
                MediaType.IMAGE_PNG_VALUE,
                "thumbnail".getBytes()
        );
    }
}