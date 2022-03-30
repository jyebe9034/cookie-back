package com.example.cookie.user.controller;

import com.example.cookie.common.BaseController;
import com.example.cookie.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService service;

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login() {
        return "";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout() {
        return "";
    }

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public String join() {
        return "";
    }

    /**
     * 마이페이지 내 정보 조회
     */
    @GetMapping("/my/info/{userSeq}")
    public String selectMyInfo() {
        return "";
    }

    /**
     * 마이페이지 내 정보 수정
     */
    @PostMapping("/my/info/{userSeq}")
    public String updateMyInfo() {
        return "";
    }

    /**
     * 마이페이지 탈퇴
     */
    @DeleteMapping("/my/info/{userSeq}")
    public String deleteMyInfo() {
        return "";
    }

    /**
     * 좋아요 목록
     */
    @GetMapping("/my/likedList/{userSeq}")
    public String selectMyLikedList() {
        return "";
    }

    /**
     * 작성 글 목록
     */
    @GetMapping("/my/boardList/{userSeq}")
    public String selectMyBoardList() {
        return "";
    }

    /**
     * 작성 댓글 목록
     */
    @GetMapping("/my/commentList/{userSeq}")
    public String selectMyCommentList() {
        return "";
    }

    /**
     * 추천 웹툰 목록
     */
    @GetMapping("/my/recommendList/{userSeq}")
    public String selectMyRecommendList() {
        return "";
    }
}
