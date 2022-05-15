package com.example.cookie.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardImage is a Querydsl query type for BoardImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardImage extends EntityPathBase<BoardImage> {

    private static final long serialVersionUID = -964721378L;

    public static final QBoardImage boardImage = new QBoardImage("boardImage");

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QBoardImage(String variable) {
        super(BoardImage.class, forVariable(variable));
    }

    public QBoardImage(Path<? extends BoardImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardImage(PathMetadata metadata) {
        super(BoardImage.class, metadata);
    }

}

