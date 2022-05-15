package com.example.cookie.webtoon.service;

import com.example.cookie.webtoon.domain.Webtoon;
import com.example.cookie.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WebtoonService {

    //private final NaverWebtoonSearch naverSearch;

    private final WebtoonRepository repository;

    @Transactional
    public Webtoon insertWebtoon(Webtoon webtoon) {
        return repository.save(webtoon);
    }

    public List<Webtoon> selectWebtoonList() {
        return repository.findAll();
    }

    /**
     * 웹툰 검색
     * @param title
     * @return
     */
    public List<Webtoon> findWebtoonByTitle(String title) {
        List<Webtoon> result = new ArrayList<>();

        // 1. DB에 있는 웹툰인지 조회
        Optional<Webtoon> byTitle = repository.findByTitle(title);
        if (byTitle.isPresent()) {
            result.add(byTitle.get());
            return result;
        }

        // 2. DB에 없는 경우 naver에 검색해서 웹툰정보 조회(최대 5개)
        //result = naverSearch.activeBot(title);

        return result;
    }
}
