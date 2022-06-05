package com.example.cookie.webtoon.service;

import com.example.cookie.user.repository.UserRepository;
import com.example.cookie.webtoon.domain.Webtoon;
import com.example.cookie.webtoon.domain.WebtoonDTO;
import com.example.cookie.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WebtoonService {

    //private final NaverWebtoonSearch naverSearch;

    private final WebtoonRepository repository;
    private final UserRepository userRepository;

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


    /**
     * 취향별 웹툰 추천목록 조회
     * @param userSeq
     * @return
     */
    public List<WebtoonDTO> selectMyRecommendList(Long userSeq) {
        // FIXME 제목, 작가, 플랫폼, 웹툰 첫화 링크, 장르, 썸네일(해당 달글 존재 시 달글로 이동??)
        List<WebtoonDTO> result = new ArrayList<>();
        // 일단 유저의 웹툰 시퀀스 배열을 조회해 온다.
        int[] recommendSeqList = userRepository.selectRecommendWebtoonSeq(userSeq);
        Arrays.stream(recommendSeqList).asLongStream().forEach(seq ->
        {
            Optional<Webtoon> byId = repository.findById(seq);
            if (byId.isPresent()) {
                Webtoon webtoon = byId.get();
                WebtoonDTO dto = new WebtoonDTO();
                dto.setWebtoonSeq(webtoon.getWebtoonSeq());
                dto.setTitle(webtoon.getTitle());
                dto.setWriter(webtoon.getWriter());
                dto.setGenre(webtoon.getGenre());
                dto.setLink(webtoon.getLink());
                dto.setThumbnail(webtoon.getThumbnail());
                result.add(dto);
            }
        });

        return result;
    }
}
