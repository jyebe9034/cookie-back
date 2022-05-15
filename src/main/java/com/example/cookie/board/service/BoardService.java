package com.example.cookie.board.service;

import com.example.cookie.board.domain.*;
import com.example.cookie.board.repository.BoardRepository;
import com.example.cookie.board.repository.LikedRepository;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;
    private final LikedRepository likedRepository;

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

    public List<BoardListResponseDto> findAllDesc() {
        return repository.findAllDesc();
    }

    public List<BoardListResponseDto> findSearchBoard(BoardListResponseDto dto) {
        return repository.findAllByTitle(dto.getTitle());
    }

    public BoardResponseDto findByBoardSeq(Long boardSeq) {
        return repository.findByBoardSeq(boardSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq = " + boardSeq));
    }

    @Transactional
    public Long save(BoardSaveRequestDto dto) {
        return repository.save(dto.toEntity()).getSeq();
    }

    @Transactional
    public Long update(Long boardSeq, BoardUpdateRequestDto dto) {
        Board board = repository.findById(boardSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq ="  + boardSeq));
        board.update(board.getWebtoonSeq(), board.getTitle(), board.getContents());
        return boardSeq;
    }

    @Transactional
    public Map<String, Object> delete(Long boardSeq) {
        Board board = repository.findById(boardSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq = " + boardSeq));
        repository.delete(board);
        return MessageUtil.setResultMsg(Message.성공);
    }

    public int findBoardLiked(Long boardSeq) {
        return likedRepository.findByBoardSeq(boardSeq);
    }

    @Transactional
    public Long saveBoardLike(LikeRequestDto dto) {
        return likedRepository.save(dto.toEntity()).getBoardSeq();
    }

    @Transactional
    public Map<String, Object> deleteBoardLiked(LikeRequestDto dto) {
        Liked liked = likedRepository.findByBoardSeqAndUserSeq(dto.getBoardSeq(), dto.getUserSeq())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq = " + dto.getBoardSeq()));
        likedRepository.delete(liked);
        return MessageUtil.setResultMsg(Message.성공);
    }
}
