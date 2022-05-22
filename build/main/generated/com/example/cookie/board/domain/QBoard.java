package com.example.cookie.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1746202051L;

    public static final QBoard board = new QBoard("board");

    public final com.example.cookie.common.QBaseDomain _super = new com.example.cookie.common.QBaseDomain(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Integer> readCount = createNumber("readCount", Integer.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> webtoonSeq = createNumber("webtoonSeq", Long.class);

    //inherited
    public final NumberPath<Long> writer = _super.writer;

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

