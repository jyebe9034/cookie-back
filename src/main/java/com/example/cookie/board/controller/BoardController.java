package com.example.cookie.board.controller;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.service.BoardService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/board";

    private final BoardService service;

    /**
     * 게시글 목록 조회
     */
    @GetMapping(URI_PREFIX)
    public String selectBoardList() {
        return "";
    }

    /**
     * 게시글 조회
     */
    @GetMapping(URI_PREFIX + "/{boardSeq}")
    public String selectBoard() {
        return "";
    }

    /**
     * 게시글 등록
     */
    @PostMapping(URI_PREFIX)
    public String insertBoard() {
        return "";
    }

    /**
     * 게시글 수정
     */
    @PutMapping(URI_PREFIX + "/{boardSeq}")
    public String updateBoard() {
        return "";
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(URI_PREFIX +"/{boardSeq}")
    public String deleteBoard() {
        return "";
    }

    /**
     * 게시글 검색
     */
    @GetMapping(URI_PREFIX + "/{keyword}")
    public String searchBoard() {
        return "";
    }

    /**
     * 게시글 좋아요 조회
     */
    @GetMapping(URI_PREFIX + "/like/{boardSeq}")
    public String selectBoardLiked() {
        return "";
    }

    /**
     * 게시글 좋아요 클릭
     */
    @PostMapping(URI_PREFIX + "/like/{userSeq}")
    public String clickBoardLike() {
        return "";
    }

    /**
     * 게시글 좋아요 클릭 해제
     */
    @PutMapping(URI_PREFIX + "/like/{userSeq}")
    public String disabledBoardLike() {
        return "";
    }

    /**
     * 마이페이지 - 좋아요 목록
     */
    @GetMapping(URI_PREFIX + "/likedList/{userSeq}")
    public ResponseEntity<List<Board>> selectMyLikedList(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, service.selectMyLikedList(userSeq));
    }

    /**
     * 마이페이지 - 작성 글 목록
     */
    @GetMapping(URI_PREFIX + "/myList/{userSeq}")
    public ResponseEntity<List<Board>> selectMyBoardList(@PathVariable("userSeq") Long userSeq) {
        return createResponseEntity(true, service.selectMyBoardList(userSeq));
    }

}
