package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositorySupport {

    List<Board> findAllByWriter(Long userSeq);

    List<Board> findTop5ByOrderByCreateDateDesc();
}
