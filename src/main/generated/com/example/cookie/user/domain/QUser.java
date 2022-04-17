package com.example.cookie.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1077358219L;

    public static final QUser user = new QUser("user");

    public final StringPath birthYear = createString("birthYear");

    public final StringPath id = createString("id");

    public final BooleanPath isLeave = createBoolean("isLeave");

    public final DatePath<java.time.LocalDate> joinDate = createDate("joinDate", java.time.LocalDate.class);

    public final StringPath mbti = createString("mbti");

    public final StringPath nickname = createString("nickname");

    public final StringPath platform = createString("platform");

    public final StringPath profileImage = createString("profileImage");

    public final StringPath role = createString("role");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final ArrayPath<String[], String> taste = createArray("taste", String[].class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

