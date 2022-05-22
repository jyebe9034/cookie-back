package com.example.cookie.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDomain is a Querydsl query type for BaseDomain
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseDomain extends EntityPathBase<BaseDomain> {

    private static final long serialVersionUID = 1500744895L;

    public static final QBaseDomain baseDomain = new QBaseDomain("baseDomain");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> writer = createNumber("writer", Long.class);

    public QBaseDomain(String variable) {
        super(BaseDomain.class, forVariable(variable));
    }

    public QBaseDomain(Path<? extends BaseDomain> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDomain(PathMetadata metadata) {
        super(BaseDomain.class, metadata);
    }

}

