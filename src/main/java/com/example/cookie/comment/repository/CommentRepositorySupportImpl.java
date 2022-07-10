package com.example.cookie.comment.repository;

import com.example.cookie.comment.domain.CommentDto;
import com.example.cookie.comment.domain.QComment;
import com.example.cookie.user.domain.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositorySupportImpl implements CommentRepositorySupport {

    private final JPAQueryFactory factory;

    QUser user = QUser.user;
    QComment comment = QComment.comment;

    @Override
    public List<CommentDto> findAllByBoardSeq(Long boardSeq) {
        return factory.select(
                Projections.constructor(
                        CommentDto.class,
                        comment, user.nickname, user.profileImage
                )
        ).from(comment)
        .leftJoin(user).on(comment.writer.eq(user.seq))
        .orderBy(comment.createDate.desc())
        .fetch();
    }
}

