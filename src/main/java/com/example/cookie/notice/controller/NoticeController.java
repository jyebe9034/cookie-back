package com.example.cookie.notice.controller;

import com.example.cookie.common.BaseController;
import com.example.cookie.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/notice";

    private final NoticeService service;

    /**
     * 공지사항 목록 조회
     */
    @GetMapping(URI_PREFIX)
    public String selectNoticeList() {
        return "";
    }

    /**
     * 공지사항 조회
     */
    @GetMapping(URI_PREFIX + "/{noticeSeq}")
    public String selectNotice() {
        return "";
    }
}
