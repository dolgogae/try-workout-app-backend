package com.ptfinder.ptfinderback.domain.gym.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGym is a Querydsl query type for Gym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGym extends EntityPathBase<Gym> {

    private static final long serialVersionUID = 1875970254L;

    public static final QGym gym = new QGym("gym");

    public final StringPath address = createString("address");

    public final StringPath businessHoursWeek = createString("businessHoursWeek");

    public final StringPath businessHoursWeekend = createString("businessHoursWeekend");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath gymName = createString("gymName");

    public final StringPath holiday = createString("holiday");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath information = createString("information");

    public final NumberPath<Integer> lockerFee = createNumber("lockerFee", Integer.class);

    public final BooleanPath lockerYn = createBoolean("lockerYn");

    public final NumberPath<Float> mapX = createNumber("mapX", Float.class);

    public final NumberPath<Float> mapY = createNumber("mapY", Float.class);

    public final NumberPath<Integer> parkingFee = createNumber("parkingFee", Integer.class);

    public final BooleanPath parkingYn = createBoolean("parkingYn");

    public final StringPath roadAddress = createString("roadAddress");

    public final ListPath<com.ptfinder.ptfinderback.domain.trainer.data.Trainer, com.ptfinder.ptfinderback.domain.trainer.data.QTrainer> trainers = this.<com.ptfinder.ptfinderback.domain.trainer.data.Trainer, com.ptfinder.ptfinderback.domain.trainer.data.QTrainer>createList("trainers", com.ptfinder.ptfinderback.domain.trainer.data.Trainer.class, com.ptfinder.ptfinderback.domain.trainer.data.QTrainer.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> wearFee = createNumber("wearFee", Integer.class);

    public final BooleanPath wearYn = createBoolean("wearYn");

    public QGym(String variable) {
        super(Gym.class, forVariable(variable));
    }

    public QGym(Path<? extends Gym> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGym(PathMetadata metadata) {
        super(Gym.class, metadata);
    }

}

