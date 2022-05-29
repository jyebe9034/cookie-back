package com.example.cookie.user.repository;

import com.example.cookie.user.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final JPAQueryFactory factory;

    QUser user = QUser.user;


    @Override
    public int[] selectRecommendWebtoonSeq(Long userSeq) {
        return factory.select(user.recommendWebtoonSeq)
                .from(user)
                .where(user.seq.eq(userSeq)).fetchOne();
    }
}
