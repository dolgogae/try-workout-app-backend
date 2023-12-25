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

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath accessToken = createString("accessToken");

    public final EnumPath<com.tryworkout.backend.domain.user.AccountType> accountType = createEnum("accountType", com.tryworkout.backend.domain.user.AccountType.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<com.tryworkout.backend.domain.trainer.data.Trainer, com.tryworkout.backend.domain.trainer.data.QTrainer> trainers = this.<com.tryworkout.backend.domain.trainer.data.Trainer, com.tryworkout.backend.domain.trainer.data.QTrainer>createList("trainers", com.tryworkout.backend.domain.trainer.data.Trainer.class, com.tryworkout.backend.domain.trainer.data.QTrainer.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public final EnumPath<com.tryworkout.backend.domain.user.UserRole> userRole = createEnum("userRole", com.tryworkout.backend.domain.user.UserRole.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}
