package com.example.cookie.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikedId is a Querydsl query type for LikedId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLikedId extends BeanPath<LikedId> {

    private static final long serialVersionUID = 1354219327L;

    public static final QLikedId likedId = new QLikedId("likedId");

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final DatePath<java.time.LocalDate> createDate = createDate("createDate", java.time.LocalDate.class);

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QLikedId(String variable) {
        super(LikedId.class, forVariable(variable));
    }

    public QLikedId(Path<? extends LikedId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikedId(PathMetadata metadata) {
        super(LikedId.class, metadata);
    }

}

