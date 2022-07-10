package com.example.cookie.comment.repository;

import com.example.cookie.comment.domain.CommentDto;

import java.util.List;

public interface CommentRepositorySupport {

    List<CommentDto> findAllByBoardSeq(Long boardSeq);
}
