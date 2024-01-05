package com.tryworkout.backend.domain.filestorage.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQualification is a Querydsl query type for Qualification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQualification extends EntityPathBase<Qualification> {

    private static final long serialVersionUID = -1214235317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQualification qualification = new QQualification("qualification");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.tryworkout.backend.domain.trainer.data.QTrainer trainer;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath url = createString("url");

    public QQualification(String variable) {
        this(Qualification.class, forVariable(variable), INITS);
    }

    public QQualification(Path<? extends Qualification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQualification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQualification(PathMetadata metadata, PathInits inits) {
        this(Qualification.class, metadata, inits);
    }

    public QQualification(Class<? extends Qualification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new com.tryworkout.backend.domain.trainer.data.QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

