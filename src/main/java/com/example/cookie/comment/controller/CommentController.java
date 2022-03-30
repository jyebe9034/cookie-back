package com.example.cookie.comment.controller;

import com.example.cookie.comment.service.CommentService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

    private final CommentService service;

    /**
     * 댓글 조회
     */
    @GetMapping("/comment/{boardSeq}")
    public String selectComment() {
        return "";
    }

    /**
     * 댓글 등록
     */
    @PostMapping("/comment")
    public String insertComment() {
        return "";
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/comment/{userSeq}")
    public String updateComment() {
        return "";
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/comment/{userSeq}")
    public String deleteComment() {
        return "";
    }
}
