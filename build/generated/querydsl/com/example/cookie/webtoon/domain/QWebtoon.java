package com.example.cookie.webtoon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = -1866582723L;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath genre = createString("genre");

    public final StringPath link = createString("link");

    public final StringPath platform = createString("platform");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final NumberPath<Long> webtoonSeq = createNumber("webtoonSeq", Long.class);

    public final StringPath writer = createString("writer");

    public QWebtoon(String variable) {
        super(Webtoon.class, forVariable(variable));
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoon(PathMetadata metadata) {
        super(Webtoon.class, metadata);
    }

}

