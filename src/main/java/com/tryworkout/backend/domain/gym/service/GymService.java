package com.tryworkout.backend.domain.gym.service;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.*;
import com.tryworkout.backend.domain.gym.repository.GymDslRepository;
import com.tryworkout.backend.domain.gym.repository.GymJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * TODO: GYM 등록과 수정 권한은 누구에게 줘야하나 결정해야함(특히, 수정 권한)
 * 아이디어1
 * Gym을 등록한 트레이너에게만 수정 권한을 주고 소속 트레이너들에게 노티를 주는 방법?
  */
@Service @Slf4j
@RequiredArgsConstructor
public class GymService {

    private final GymDslRepository gymDslRepository;
    private final GymJpaRepository gymJpaRepository;
    private final TrainerJpaRepository trainerJpaRepository;
    private final ModelMapper mapper;

    public GymDto getGym(GymCreateDto gymCreateDto) {
        Optional<Gym> findGyms = gymDslRepository.findOneByGymNameAndMapXAndMapY(gymCreateDto);

        if(findGyms.isEmpty()){
            return createGym(gymCreateDto);
        } else {
            return mapper.map(findGyms.get(), GymDto.class);
        }
    }

    public GymDto updateGymFees(GymFeeUpdateDto gymFeeUpdateDto) {
        Gym gym = gymJpaRepository.findById(gymFeeUpdateDto.getGymId()).orElseThrow(() ->
                new BusinessException(ErrorCode.GYM_NOT_FOUND));

        Gym savedGym = gymJpaRepository.save(gym.updateGymFees(gymFeeUpdateDto));

        GymDto result = mapper.map(savedGym, GymDto.class);
        log.info("update gym's fee = {}", gymFeeUpdateDto);

        return result;
    }

    public GymDto updateGymOperatingTime(GymOperatingTimeDto gymOperatingTimeDto){
        Gym gym = gymJpaRepository.findById(gymOperatingTimeDto.getGymId()).orElseThrow(() ->
                new BusinessException(ErrorCode.GYM_NOT_FOUND));
        Gym savedGym = gymJpaRepository.save(gym.updateOperatingTime(gymOperatingTimeDto));

        GymDto result = mapper.map(savedGym, GymDto.class);
        log.info("update gym's operating time = {}", gymOperatingTimeDto);

        return result;
    }

    public GymDto updateGymInformation(GymInfoUpdateDto gymInfoUpdateDto){
        Gym gym = gymJpaRepository.findById(gymInfoUpdateDto.getGymId()).orElseThrow(() ->
                new BusinessException(ErrorCode.GYM_NOT_FOUND));
        Gym savedGym = gymJpaRepository.save(gym.updateGymInfo(gymInfoUpdateDto));

        GymDto result = mapper.map(savedGym, GymDto.class);
        log.info("update gym's information = {}", gymInfoUpdateDto);

        return result;
    }


    /**
     * 나중에 지역으로만 먼저 나눠서 찾으면 훨씬 최적화 될듯
     * 근데 체육관이 아무리 많아봤자 1만개가 될까 싶기도 함.
     * @param k
     * @param gymLocation
     * @return
     */
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

    public Boolean isGymUpdateAuth(Long trainerId, Long gymId){
        Trainer trainer = trainerJpaRepository.findById(trainerId).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        return gymId.equals(trainer.getGym().getId());
    }

    public GymDto createGym(GymCreateDto gymCreateDto) {
        Gym gym = Gym.create(gymCreateDto);
        Gym savedGym = gymJpaRepository.save(gym);
        GymDto result = mapper.map(savedGym, GymDto.class);
        log.info("create Gym info = {}", result);

        return result;
    }
}
