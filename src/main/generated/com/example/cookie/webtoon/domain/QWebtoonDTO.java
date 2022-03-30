package com.example.cookie.webtoon.domain;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.cookie.webtoon.domain.QWebtoonDTO is a Querydsl Projection type for WebtoonDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QWebtoonDTO extends ConstructorExpression<WebtoonDTO> {

    private static final long serialVersionUID = -424251550L;

    public QWebtoonDTO(com.querydsl.core.types.Expression<Long> webtoonSeq, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> writer, com.querydsl.core.types.Expression<String> genre, com.querydsl.core.types.Expression<String> thumbnail, com.querydsl.core.types.Expression<String> link, com.querydsl.core.types.Expression<String> platform, com.querydsl.core.types.Expression<Integer> age) {
        super(WebtoonDTO.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, int.class}, webtoonSeq, title, writer, genre, thumbnail, link, platform, age);
    }

}

