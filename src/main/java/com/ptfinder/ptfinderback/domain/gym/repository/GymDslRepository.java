package com.ptfinder.ptfinderback.domain.gym.repository;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.gym.dto.GymCreateDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ptfinder.ptfinderback.domain.gym.data.QGym.gym;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class GymDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Gym> findOneByGymNameAndMapXAndMapY(GymCreateDto createCondition){
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(gym)
                .where(
                        gymNameEq(createCondition.getGymName()),
                        mapXEq(createCondition.getMapX()),
                        mapYEq(createCondition.getMapY())
                )
                .fetchOne());
    }

    private BooleanExpression gymNameEq(String gymName){
        return hasText(gymName) ? null : gym.gymName.eq(gymName);
    }

    private BooleanExpression mapXEq(Float mapX){
        return mapX == null ? null : gym.mapX.eq(mapX);
    }

    private BooleanExpression mapYEq(Float mapY){
        return mapY == null ? null : gym.mapX.eq(mapY);
    }
}
