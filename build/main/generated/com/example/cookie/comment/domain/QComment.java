package com.example.cookie.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 457884957L;

    public static final QComment comment = new QComment("comment");

    public final com.example.cookie.common.QBaseDomain _super = new com.example.cookie.common.QBaseDomain(this);

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final NumberPath<Long> commentSeq = createNumber("commentSeq", Long.class);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> parentSeq = createNumber("parentSeq", Long.class);

    public final StringPath url = createString("url");

    //inherited
    public final NumberPath<Long> writer = _super.writer;

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

