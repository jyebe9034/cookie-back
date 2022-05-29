package com.example.cookie.webtoon.controller;

import com.example.cookie.common.BaseController;
import com.example.cookie.webtoon.domain.Webtoon;
import com.example.cookie.webtoon.domain.WebtoonDTO;
import com.example.cookie.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebtoonController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/webtoon";

    private final WebtoonService service;

    /**
     * 웹툰 검색
     * @param title
     * @return
     */
    @GetMapping(URI_PREFIX + "/search/{title}")
    public ResponseEntity<List<Webtoon>> findWebtoonByTitle(@PathVariable("title") String title) {
        return createResponseEntity(true, service.findWebtoonByTitle(title));
    }

    @GetMapping(URI_PREFIX + "/test")
    public ResponseEntity<List<WebtoonDTO>> testList() {
        long seq = 20;
        return createResponseEntity(true, service.selectMyRecommendList(seq));
    }

}
