package com.tryworkout.backend.domain.member.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1082981275L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> exercisePeriodYear = createNumber("exercisePeriodYear", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.tryworkout.backend.domain.reservation.data.Reservation, com.tryworkout.backend.domain.reservation.data.QReservation> reservations = this.<com.tryworkout.backend.domain.reservation.data.Reservation, com.tryworkout.backend.domain.reservation.data.QReservation>createList("reservations", com.tryworkout.backend.domain.reservation.data.Reservation.class, com.tryworkout.backend.domain.reservation.data.QReservation.class, PathInits.DIRECT2);

    public final ListPath<com.tryworkout.backend.domain.review.data.Review, com.tryworkout.backend.domain.review.data.QReview> reviews = this.<com.tryworkout.backend.domain.review.data.Review, com.tryworkout.backend.domain.review.data.QReview>createList("reviews", com.tryworkout.backend.domain.review.data.Review.class, com.tryworkout.backend.domain.review.data.QReview.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.tryworkout.backend.domain.user.data.QUserEntity user;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.tryworkout.backend.domain.user.data.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

