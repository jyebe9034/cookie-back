package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Liked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    Optional<Liked> findByBoardSeqAndUserSeq(Long boardSeq, Long userSeq);
}
