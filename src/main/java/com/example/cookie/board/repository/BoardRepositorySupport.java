package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;

import java.util.List;

public interface BoardRepositorySupport {

    List<Board> selectMyLikedList(Long userSeq);
}
