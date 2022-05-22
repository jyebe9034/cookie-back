package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.domain.BoardListResponseDto;
import com.example.cookie.board.domain.BoardResponseDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepositorySupport {

    List<Board> selectMyLikedList(Long userSeq);

    List<BoardListResponseDto> findAllDesc();

    List<BoardListResponseDto> findAllByTitle(String title);

    Optional<BoardResponseDto> findByBoardSeq(Long boardSeq);
}
