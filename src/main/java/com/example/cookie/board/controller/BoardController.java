package com.example.cookie.board.controller;

import com.example.cookie.board.domain.dto.*;
import com.example.cookie.board.service.BoardService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/board";

    private final BoardService service;

    /**
     * 게시글 목록 조회
     * @return
     */
    @GetMapping(URI_PREFIX)
    public ResponseEntity<Map<String, Object>> selectBoardList() {
        return createResponseEntity(true, service.findAllDesc());
    }

    /**
     * 게시글 검색
     */
    @GetMapping(URI_PREFIX + "/search")
    public ResponseEntity<Map<String, Object>> searchBoard(@RequestParam String title) {
        return createResponseEntity(true, service.findByTitle(title));
    }

    /**
     * 게시글 조회
     */
    @GetMapping(URI_PREFIX + "/{boardSeq}")
    public ResponseEntity<Map<String, Object>> selectBoard(@PathVariable Long boardSeq) {
        return createResponseEntity(true, service.findById(boardSeq));
    }

    /**
     * 게시글 등록
     */
    @PostMapping(URI_PREFIX)
    public ResponseEntity<Map<String, Object>> save(@RequestBody BoardSaveRequestDto dto, Authentication authentication) {
        return createResponseEntity(true, service.save(dto, authentication));
    }

    /**
     * 게시글 수정
     */
    @PutMapping(URI_PREFIX + "/{boardSeq}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long boardSeq, @RequestBody BoardUpdateRequestDto dto, Authentication authentication) {
        return createResponseEntity(true, service.update(boardSeq, dto, authentication));
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(URI_PREFIX +"/{boardSeq}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long boardSeq) {
        return createResponseEntity(true, service.delete(boardSeq));
    }

    /**
     * 게시글 좋아요 클릭
     */
    @PostMapping(URI_PREFIX + "/like")
    public ResponseEntity<Map<String, Object>> clickBoardLike(@RequestBody LikeRequestDto dto, Authentication authentication) {
        return createResponseEntity(true, service.saveLike(dto, authentication));
    }

    /**
     * 메인화면에서 노출되는 인기달글, 신규달글
     * @return
     */
    @GetMapping(URI_PREFIX + "/main/best")
    public ResponseEntity<Map<String, Object>> selectMainBoardList() {
        return createResponseEntity(true, service.selectMainBoardList());
    }

    /**
     * 게시글 내용 이미지 업로드
     * @param multipartFile
     * @return
     */
    @PostMapping(URI_PREFIX + "/editor/image")
    public ResponseEntity<Map<String, Object>> uploadContentImage(MultipartHttpServletRequest multipartFile) throws IOException {
        return createResponseEntity(true, service.uploadContentImage(multipartFile.getFile("contentImage")));
    }
}
