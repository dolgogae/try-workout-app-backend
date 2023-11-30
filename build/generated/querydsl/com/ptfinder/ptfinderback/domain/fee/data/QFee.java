package com.ptfinder.ptfinderback.domain.fee.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFee is a Querydsl query type for Fee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFee extends EntityPathBase<Fee> {

    private static final long serialVersionUID = 603315502L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFee fee = new QFee("fee");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Float> discountRate = createNumber("discountRate", Float.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> times = createNumber("times", Integer.class);

    public final com.ptfinder.ptfinderback.domain.trainer.data.QTrainer trainer;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QFee(String variable) {
        this(Fee.class, forVariable(variable), INITS);
    }

    public QFee(Path<? extends Fee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFee(PathMetadata metadata, PathInits inits) {
        this(Fee.class, metadata, inits);
    }

    public QFee(Class<? extends Fee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new com.ptfinder.ptfinderback.domain.trainer.data.QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

