package com.example.cookie.comment.controller;

import com.example.cookie.comment.domain.Comment;
import com.example.cookie.comment.domain.CommentFormData;
import com.example.cookie.comment.service.CommentService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

    private final CommentService service;

    /**
     * 댓글 조회
     */
    @GetMapping("/comment/{boardSeq}")
    public ResponseEntity<List<Comment>> selectComment(@PathVariable("boardSeq") Long boardSeq) {
        return createResponseEntity(true, service.selectComment(boardSeq));
    }

    /**
     * 댓글 등록
     */
    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> insertComment(CommentFormData formData) {
        return createResponseEntity(true, service.insertComment(formData));
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/comment")
    public ResponseEntity<Map<String, Object>> updateComment(CommentFormData formData) {
        return createResponseEntity(true, service.updateComment(formData));
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/comment/{commentSeq}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("commentSeq") Long commentSeq) {
        return createResponseEntity(true, service.deleteComment(commentSeq));
    }
}
