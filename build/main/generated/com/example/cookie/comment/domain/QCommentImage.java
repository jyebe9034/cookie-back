package com.example.cookie.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentImage is a Querydsl query type for CommentImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentImage extends EntityPathBase<CommentImage> {

    private static final long serialVersionUID = -1017340354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentImage commentImage = new QCommentImage("commentImage");

    public final QComment comment;

    public final NumberPath<Long> commentImageSeq = createNumber("commentImageSeq", Long.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public QCommentImage(String variable) {
        this(CommentImage.class, forVariable(variable), INITS);
    }

    public QCommentImage(Path<? extends CommentImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentImage(PathMetadata metadata, PathInits inits) {
        this(CommentImage.class, metadata, inits);
    }

    public QCommentImage(Class<? extends CommentImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
    }

}

