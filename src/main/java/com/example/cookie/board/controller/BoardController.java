package com.example.cookie.board.controller;

import com.example.cookie.board.domain.*;
import com.example.cookie.board.service.BoardService;
import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<BoardListResponseDto>> selectBoardList() {
        return createResponseEntity(true, service.findAllDesc());
    }

    /**
     * 게시글 검색
     */
    @GetMapping(URI_PREFIX + "/search")
    public ResponseEntity<List<Board>> searchBoard(@RequestBody BoardListResponseDto dto) {
        return createResponseEntity(true, service.findSearchBoard(dto));
    }

    /**
     * 게시글 조회
     */
    @GetMapping(URI_PREFIX + "/{boardSeq}")
    public ResponseEntity<Board> selectBoard(@PathVariable Long boardSeq) {
        return createResponseEntity(true, service.findByBoardSeq(boardSeq));
    }

    /**
     * 게시글 등록
     */
    @GetMapping(URI_PREFIX + "/save")
    public String saveBoard() {
        return "board-save";
    }
    @PostMapping(URI_PREFIX)
    public ResponseEntity<Map<String, Object>> save(@RequestBody BoardSaveRequestDto dto) {
        return createResponseEntity(true, service.save(dto));
    }

    /**
     * 게시글 수정
     */
    @GetMapping(URI_PREFIX + "/update/{boardSeq}")
    public String boardUpdate(@PathVariable Long boardSeq, Model model) {
        BoardResponseDto dto = service.findByBoardSeq(boardSeq);
        model.addAttribute("board", dto);
        return "board-update";
    }
    @PutMapping(URI_PREFIX + "/{boardSeq}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long boardSeq, @RequestBody BoardUpdateRequestDto dto) {
        return createResponseEntity(true, service.update(boardSeq, dto));
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(URI_PREFIX +"/{boardSeq}")
    public ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable Long boardSeq) {
        return createResponseEntity(true, service.delete(boardSeq));
    }

    /**
     * 게시글 좋아요 조회
     */
    @GetMapping(URI_PREFIX + "/like/{boardSeq}")
    public ResponseEntity<Integer> selectBoardLiked(@PathVariable("boardSeq") Long boardSeq) {
        return createResponseEntity(true, service.findBoardLiked(boardSeq));
    }

    /**
     * 게시글 좋아요 클릭
     */
    @PostMapping(URI_PREFIX + "/like")
    public ResponseEntity<Map<String, Object>> clickBoardLike(@RequestBody LikeRequestDto dto) {
        return createResponseEntity(true, service.saveBoardLike(dto));
    }

    /**
     * 게시글 좋아요 클릭 해제
     */
    @DeleteMapping(URI_PREFIX + "/like")
    public ResponseEntity<Map<String, Object>> disabledBoardLike(@RequestBody LikeRequestDto dto) {
        return createResponseEntity(true, service.deleteBoardLiked(dto));
    }

    /**
     * 메인화면에서 노출되는 인기달글, 신규달글
     * @return
     */
    @GetMapping(URI_PREFIX + "/main/best")
    public ResponseEntity<Map<String, Object>> selectMainBoardList() {
        return createResponseEntity(true, service.selectMainBoardList());
    }
}
