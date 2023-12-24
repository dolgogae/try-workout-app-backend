package com.tryworkout.backend.domain.gym.repository;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.GymCreateDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tryworkout.backend.domain.gym.data.QGym;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class GymDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Gym> findOneByGymNameAndMapXAndMapY(GymCreateDto createCondition){
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(QGym.gym)
                .where(
                        gymNameEq(createCondition.getGymName()),
                        mapXEq(createCondition.getMapX()),
                        mapYEq(createCondition.getMapY())
                )
                .fetchOne());
    }

    private BooleanExpression gymNameEq(String gymName){
        return hasText(gymName) ? null : QGym.gym.gymName.eq(gymName);
    }

    private BooleanExpression mapXEq(Float mapX){
        return mapX == null ? null : QGym.gym.mapX.eq(mapX);
    }

    private BooleanExpression mapYEq(Float mapY){
        return mapY == null ? null : QGym.gym.mapX.eq(mapY);
    }
}
