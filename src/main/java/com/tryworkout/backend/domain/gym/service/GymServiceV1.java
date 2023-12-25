package com.tryworkout.backend.domain.gym.service;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.GymDto;
import com.tryworkout.backend.domain.gym.dto.GymCreateDto;
import com.tryworkout.backend.domain.gym.repository.GymJpaRepository;
import com.tryworkout.backend.domain.gym.dto.GymLocationDto;
import com.tryworkout.backend.domain.gym.repository.GymDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * 나중에 지역으로만 먼저 나눠서 찾으면 훨씬 최적화 될듯
     * 근데 체육관이 아무리 많아봤자 1만개가 될까 싶기도 함.
     * @param k
     * @param gymLocation
     * @return
     */
    @Override
    public List<GymLocationDto> getKNearestLocations(Integer k, GymLocationDto gymLocation) {
        List<Gym> allGyms = gymJpaRepository.findAll();
        List<GymLocationDto> allGymsDto = allGyms.stream().map(gym -> mapper.map(gym, GymLocationDto.class)).toList();

        return findKClosestPoints(gymLocation, allGymsDto, k);
    }

    private List<GymLocationDto> findKClosestPoints(GymLocationDto origin, List<GymLocationDto> others, int k) {

        PriorityQueue<GymLocationDto> priorityQueue = new PriorityQueue<>();
        for (GymLocationDto other : others) {
            other.calculateDistance(origin);
        }
        priorityQueue.addAll(others);

        List<GymLocationDto> result = new ArrayList<>();
        int n = Math.min(priorityQueue.size(), k);
        for(int i = 0; i < n; ++i){
            result.add(priorityQueue.poll());
        }

        return result;
    }
    public GymDto createGym(GymCreateDto gymCreateDto) {
        Gym gym = Gym.create(gymCreateDto);
        Gym savedGym = gymJpaRepository.save(gym);
        GymDto result = mapper.map(savedGym, GymDto.class);
        log.info("create Gym info = {}", result);

        return result;
    }
}