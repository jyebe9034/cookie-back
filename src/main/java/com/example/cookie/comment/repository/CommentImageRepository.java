package com.example.cookie.comment.repository;

import com.example.cookie.comment.domain.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentImageRepository extends JpaRepository<CommentImage, Long> {
}
