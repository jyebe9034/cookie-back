package com.example.cookie.board.service;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    /**
     * 내가 좋아요한 글 목록 조회
     * @param userSeq
     * @return
     */
    public List<Board> selectMyLikedList(Long userSeq) {
        return repository.selectMyLikedList(userSeq);
    }

    /**
     * 내가 작성한 글 목록 조회
     * @param userSeq
     * @return
     */
    public List<Board> selectMyBoardList(Long userSeq) {
        return repository.findAllByWriter(userSeq);
    }
}
