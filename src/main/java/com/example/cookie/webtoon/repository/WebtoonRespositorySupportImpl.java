package com.example.cookie.webtoon.repository;

import com.example.cookie.webtoon.domain.QWebtoon;
import com.example.cookie.webtoon.domain.Webtoon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebtoonRespositorySupportImpl implements WebtoonRepositorySupport {

    private final JPAQueryFactory factory;

    QWebtoon webtoon = QWebtoon.webtoon;

    /**
     * TODO 테스트 중..아니 왜 where절이 동작을 안하는 거죠?!
     * @return
     */
    @Override
    public List<Webtoon> findWebtoonListBy() {
        return factory.select(webtoon)
                .from(webtoon)
                .where(webtoon.title.eq("화가 살리에르"))
                .fetch();
    }

}
