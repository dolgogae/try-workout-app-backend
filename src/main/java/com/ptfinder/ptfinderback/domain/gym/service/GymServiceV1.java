package com.ptfinder.ptfinderback.domain.gym.service;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.gym.dto.GymCreateDto;
import com.ptfinder.ptfinderback.domain.gym.dto.GymDto;
import com.ptfinder.ptfinderback.domain.gym.dto.GymLocationDto;
import com.ptfinder.ptfinderback.domain.gym.repository.GymDslRepository;
import com.ptfinder.ptfinderback.domain.gym.repository.GymJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
public class GymServiceV1 implements GymService{

    private final GymDslRepository gymDslRepository;
    private final GymJpaRepository gymJpaRepository;
    private final ModelMapper mapper;

    @Override
    public GymDto getGym(GymCreateDto gymCreateDto) {
        Optional<Gym> findGyms = gymDslRepository.findOneByGymNameAndMapXAndMapY(gymCreateDto);

        if(findGyms.isEmpty()){
            return createGym(gymCreateDto);
        } else {
            return mapper.map(findGyms.get(), GymDto.class);
        }
    }

    @Override
    public List<GymDto> getKNearestLocations(Integer k, GymLocationDto gymLocation) {
        return null;
    }

    public GymDto createGym(GymCreateDto gymCreateDto) {
        Gym gym = Gym.create(gymCreateDto);
        Gym savedGym = gymJpaRepository.save(gym);
        GymDto result = mapper.map(savedGym, GymDto.class);
        log.info("create Gym info = {}", result);

        return result;
    }
}
