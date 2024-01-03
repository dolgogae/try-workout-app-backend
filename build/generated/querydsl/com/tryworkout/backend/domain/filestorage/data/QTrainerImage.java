package com.tryworkout.backend.domain.filestorage.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrainerImage is a Querydsl query type for TrainerImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainerImage extends EntityPathBase<TrainerImage> {

    private static final long serialVersionUID = 1764870858L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrainerImage trainerImage = new QTrainerImage("trainerImage");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.tryworkout.backend.domain.trainer.data.QTrainer trainer;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath url = createString("url");

    public QTrainerImage(String variable) {
        this(TrainerImage.class, forVariable(variable), INITS);
    }

    public QTrainerImage(Path<? extends TrainerImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrainerImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrainerImage(PathMetadata metadata, PathInits inits) {
        this(TrainerImage.class, metadata, inits);
    }

    public QTrainerImage(Class<? extends TrainerImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new com.tryworkout.backend.domain.trainer.data.QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

