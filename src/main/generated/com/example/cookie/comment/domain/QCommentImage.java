package com.example.cookie.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentImage is a Querydsl query type for CommentImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentImage extends EntityPathBase<CommentImage> {

    private static final long serialVersionUID = -1017340354L;

    public static final QCommentImage commentImage = new QCommentImage("commentImage");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QCommentImage(String variable) {
        super(CommentImage.class, forVariable(variable));
    }

    public QCommentImage(Path<? extends CommentImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentImage(PathMetadata metadata) {
        super(CommentImage.class, metadata);
    }

}

