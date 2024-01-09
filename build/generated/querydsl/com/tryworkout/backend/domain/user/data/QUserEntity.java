package com.tryworkout.backend.domain.user.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 812083072L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath accessToken = createString("accessToken");

    public final EnumPath<com.tryworkout.backend.domain.user.AccountType> accountType = createEnum("accountType", com.tryworkout.backend.domain.user.AccountType.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.tryworkout.backend.domain.member.data.QMember member;

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<com.tryworkout.backend.domain.trainer.data.Trainer, com.tryworkout.backend.domain.trainer.data.QTrainer> trainers = this.<com.tryworkout.backend.domain.trainer.data.Trainer, com.tryworkout.backend.domain.trainer.data.QTrainer>createList("trainers", com.tryworkout.backend.domain.trainer.data.Trainer.class, com.tryworkout.backend.domain.trainer.data.QTrainer.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public final EnumPath<com.tryworkout.backend.domain.user.UserRole> userRole = createEnum("userRole", com.tryworkout.backend.domain.user.UserRole.class);

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.tryworkout.backend.domain.member.data.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

