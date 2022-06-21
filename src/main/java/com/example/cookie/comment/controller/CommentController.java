package com.example.cookie.comment.controller;

import com.example.cookie.comment.domain.CommentFormData;
import com.example.cookie.comment.service.CommentService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/comment";

    private final CommentService service;

    /**
     * 댓글 등록
     */
    @PostMapping(URI_PREFIX)
    public ResponseEntity<Map<String, Object>> insertComment(CommentFormData formData) throws IOException {
        return createResponseEntity(true, service.insertComment(formData));
    }

    /**
     * 댓글 수정
     */
    @PutMapping(URI_PREFIX)
    public ResponseEntity<Map<String, Object>> updateComment(CommentFormData formData) throws IOException {
        return createResponseEntity(true, service.updateComment(formData));
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping(URI_PREFIX + "/{commentSeq}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("commentSeq") Long commentSeq) {
        return createResponseEntity(true, service.deleteComment(commentSeq));
    }

}
