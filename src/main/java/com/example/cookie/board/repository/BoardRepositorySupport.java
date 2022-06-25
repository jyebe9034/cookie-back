package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.domain.dto.BoardListResponseDto;
import com.example.cookie.board.domain.dto.BoardResponseDto;

import java.util.List;
import java.util.Optional;

public interface BoardRepositorySupport {

    List<Board> selectMyLikedList(Long userSeq);

    List<BoardListResponseDto> findAllDesc();

    List<BoardListResponseDto> findAllByTitle(String title);

    Optional<BoardResponseDto> findByBoardSeq(Long boardSeq);

    /**
     * 메인화면에서 노출되는 인기달글
     * @return
     */
    List<Board> selectBestBoardList();

}
