package com.example.cookie.user.controller;

import com.example.cookie.common.BaseController;
import com.example.cookie.security.oauth.KakaoOAuthService;
import com.example.cookie.security.oauth.NaverOAuthService;
import com.example.cookie.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService service;
    private final KakaoOAuthService kakaoOAuthService;
    private final NaverOAuthService naverOAuthService;

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
     * 네이버 로그인
     */
    @GetMapping("/user/oauth/naver")
    public ResponseEntity<String> naverOAuth(@RequestParam String code, @RequestParam String state) throws JsonProcessingException {
        log.info("code = {}, state = {}", code, state);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> requestNaverToken = new HttpEntity<>(naverOAuthService.getNaverAuthCode(code), headers);
        String naverToken = naverOAuthService.getNaverToken(requestNaverToken).getBody();
        log.info("naverToken = {}", naverToken);

        HttpEntity<MultiValueMap<String, String>> requestNaverProfile = naverOAuthService.requestNaverProfile(naverToken);
        ResponseEntity<String> naverProfile = naverOAuthService.getNaverProfile(requestNaverProfile);
        log.info("naverProfile = {}", naverProfile);

        // 로그인 or 회원가입
        return createResponseEntity(true, service.manageLoginOrJoin(naverProfile, "Naver"));
    }

    /**
     * 카카오 로그인
     * @return
     */
    @GetMapping("/user/oauth/kakao")
    public ResponseEntity<String> kakaoOAuth(@RequestParam String code) throws JsonProcessingException {
        log.info("code = {}", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> requestKakaoToken = new HttpEntity<>(kakaoOAuthService.getKakaoAuthCode(code), headers);
        String kakaoToken = kakaoOAuthService.getKakaoToken(requestKakaoToken).getBody();
        log.info("kakaoToken = {}", kakaoToken);

        HttpEntity<MultiValueMap<String, String>> requestKakaoProfile = kakaoOAuthService.requestKakaoProfile(kakaoToken);
        ResponseEntity<String> kakaoProfile = kakaoOAuthService.getKakaoProfile(requestKakaoProfile);
        log.info("kakaoProfile = {}", kakaoProfile);

        // 로그인 or 회원가입
        return createResponseEntity(true, service.manageLoginOrJoin(kakaoProfile, "Kakao"));
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
