package com.ptfinder.ptfinderback.domain.trainer.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrainer is a Querydsl query type for Trainer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainer extends EntityPathBase<Trainer> {

    private static final long serialVersionUID = -928744562L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrainer trainer = new QTrainer("trainer");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ListPath<com.ptfinder.ptfinderback.domain.fee.data.Fee, com.ptfinder.ptfinderback.domain.fee.data.QFee> fees = this.<com.ptfinder.ptfinderback.domain.fee.data.Fee, com.ptfinder.ptfinderback.domain.fee.data.QFee>createList("fees", com.ptfinder.ptfinderback.domain.fee.data.Fee.class, com.ptfinder.ptfinderback.domain.fee.data.QFee.class, PathInits.DIRECT2);

    public final com.ptfinder.ptfinderback.domain.gym.data.QGym gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final ListPath<String, StringPath> qualifications = this.<String, StringPath>createList("qualifications", String.class, StringPath.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.ptfinder.ptfinderback.domain.user.data.QUserEntity user;

    public QTrainer(String variable) {
        this(Trainer.class, forVariable(variable), INITS);
    }

    public QTrainer(Path<? extends Trainer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrainer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrainer(PathMetadata metadata, PathInits inits) {
        this(Trainer.class, metadata, inits);
    }

    public QTrainer(Class<? extends Trainer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new com.ptfinder.ptfinderback.domain.gym.data.QGym(forProperty("gym")) : null;
        this.user = inits.isInitialized("user") ? new com.ptfinder.ptfinderback.domain.user.data.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

