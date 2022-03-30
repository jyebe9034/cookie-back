package com.example.cookie.board.controller;

import com.example.cookie.board.service.BoardService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController extends BaseController {

    private final BoardService service;

    /**
     * 게시글 목록 조회
     */
    @GetMapping("/board")
    public String selectBoardList() {
        return "";
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/board/{boardSeq}")
    public String selectBoard() {
        return "";
    }

    /**
     * 게시글 등록
     */
    @PostMapping("/board")
    public String insertBoard() {
        return "";
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/board/{boardSeq}")
    public String updateBoard() {
        return "";
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/board/{boardSeq}")
    public String deleteBoard() {
        return "";
    }

    /**
     * 게시글 검색
     */
    @GetMapping("/board/{keyword}")
    public String searchBoard() {
        return "";
    }

    /**
     * 게시글 좋아요 조회
     */
    @GetMapping("/board/like/{boardSeq}")
    public String selectBoardLiked() {
        return "";
    }

    /**
     * 게시글 좋아요 클릭
     */
    @PostMapping("/board/like/{userSeq}")
    public String clickBoardLike() {
        return "";
    }

    /**
     * 게시글 좋아요 클릭 해제
     */
    @PutMapping("/board/like/{userSeq}")
    public String disabledBoardLike() {
        return "";
    }
}
