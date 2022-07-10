package com.example.cookie.comment.repository;

import com.example.cookie.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositorySupport {

    List<Comment> findAllByWriter(Long userSeq);
}
