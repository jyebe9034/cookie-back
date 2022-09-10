package com.example.cookie.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLiked is a Querydsl query type for Liked
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiked extends EntityPathBase<Liked> {

    private static final long serialVersionUID = -1737136380L;

    public static final QLiked liked = new QLiked("liked");

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final DatePath<java.time.LocalDate> createDate = createDate("createDate", java.time.LocalDate.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QLiked(String variable) {
        super(Liked.class, forVariable(variable));
    }

    public QLiked(Path<? extends Liked> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLiked(PathMetadata metadata) {
        super(Liked.class, metadata);
    }

}

