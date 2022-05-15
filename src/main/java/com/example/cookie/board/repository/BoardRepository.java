package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositorySupport {

    List<Board> findAllByWriter(Long userSeq);
}
