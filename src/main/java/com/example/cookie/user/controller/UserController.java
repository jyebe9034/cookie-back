package com.example.cookie.user.controller;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.service.BoardService;
import com.example.cookie.comment.domain.Comment;
import com.example.cookie.comment.service.CommentService;
import com.example.cookie.common.BaseController;
import com.example.cookie.security.oauth.KakaoOAuthService;
import com.example.cookie.security.oauth.NaverOAuthService;
import com.example.cookie.user.domain.User;
import com.example.cookie.user.service.UserService;
import com.example.cookie.webtoon.domain.WebtoonDTO;
import com.example.cookie.webtoon.service.WebtoonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/user";

    private final UserService service;
    private final BoardService boardService;
    private final CommentService commentService;
    private final WebtoonService webtoonService;
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
     * 네이버 로그아웃
     */
    @GetMapping("/naver/logout")
    public String naverLogout(@RequestParam String id) {
        service.naverLogout(id);
        return "success";
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
    @GetMapping(URI_PREFIX + "/info/{userSeq}")
    public ResponseEntity<User> selectMyInfo(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, service.selectMyInfo(userSeq));
    }

    /**
     * 마이페이지 내 정보 수정
     */
    @PutMapping(URI_PREFIX + "/info/{userSeq}")
    public ResponseEntity<Map<String, Object>> updateMyInfo(@PathVariable("userSeq") Long userSeq, @RequestBody User user) {
        return createResponseEntity(true, service.updateMyInfo(userSeq, user));
    }


    /**
     * 마이페이지 - 좋아요 목록
     */
    @GetMapping(URI_PREFIX + "/likedList/{userSeq}")
    public ResponseEntity<List<Board>> selectMyLikedList(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, boardService.selectMyLikedList(userSeq));
    }

    /**
     * 마이페이지 - 작성 글 목록
     */
    @GetMapping(URI_PREFIX + "/boardList/{userSeq}")
    public ResponseEntity<List<Board>> selectMyBoardList(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, boardService.selectMyBoardList(userSeq));
    }

    /**
     * 마이페이지 - 댓글 목록 조회
     */
    @GetMapping(URI_PREFIX + "/commentList/{userSeq}")
    public ResponseEntity<List<Comment>> selectMyCommentList(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, commentService.selectMyCommentList(userSeq));
    }

    /**
     * 마이페이지 탈퇴
     */
    @DeleteMapping(URI_PREFIX + "/{userSeq}")
    public ResponseEntity<Map<String, Object>> deleteMyInfo(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, service.deleteMyInfo(userSeq));
    }

    /**
     * 추천 웹툰 목록 조회
     */
    @GetMapping(URI_PREFIX + "/recommendList/{userSeq}")
    public ResponseEntity<List<WebtoonDTO>> selectMyRecommendList(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, webtoonService.selectMyRecommendList(userSeq));
    }

}
