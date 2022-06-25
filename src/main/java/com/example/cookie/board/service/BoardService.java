package com.example.cookie.board.service;

import com.example.cookie.board.domain.*;
import com.example.cookie.board.repository.BoardRepository;
import com.example.cookie.board.repository.LikedRepository;
import com.example.cookie.util.S3UploadUtil;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final S3UploadUtil s3UploadUtil;

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
        board.update(dto.getWebtoonSeq(), dto.getTitle(), dto.getContents());
        return boardSeq;
    }

    @Transactional
    public Map<String, Object> delete(Long boardSeq) {
        Board board = repository.findById(boardSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq = " + boardSeq));
        repository.delete(board);
        return MessageUtil.setResultMsg(Message.성공);
    }

    @Transactional
    public Map<String, Object> clickBoardLike(LikeRequestDto dto) {
        Optional<Liked> liked = likedRepository.findByBoardSeqAndUserSeq(dto.getBoardSeq(), dto.getUserSeq());
        if (liked.isPresent()) {
            likedRepository.delete(liked.get());
        } else {
            likedRepository.save(dto.toEntity());
        }

        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 메인화면에서 노출되는 인기달글, 신규달글
     * @return
     */
    public Map<String, Object> selectMainBoardList() {
        Map<String, Object> result = new HashMap<>();
        List<Board> boards = repository.selectBestBoardList()
                .stream().limit(5).collect(Collectors.toList());
        result.put("bestBoardList", boards);
        result.put("newBoardList", repository.findTop5ByOrderByCreateDateDesc());
        return result;
    }

    /**
     * 게시글 내용 이미지 업로드
     * @param multipartFile
     * @return
     */
    public Map<String, Object> uploadContentImage(MultipartFile multipartFile) {
        // 파일 업로드
        return s3UploadUtil.upload("boardImage", multipartFile);
    }
}
