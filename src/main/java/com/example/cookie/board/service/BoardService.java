package com.example.cookie.board.service;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.domain.Liked;
import com.example.cookie.board.domain.dto.*;
import com.example.cookie.board.repository.BoardRepository;
import com.example.cookie.board.repository.LikedRepository;
import com.example.cookie.comment.domain.CommentDto;
import com.example.cookie.comment.repository.CommentRepository;
import com.example.cookie.exception.DMException;
import com.example.cookie.user.domain.User;
import com.example.cookie.util.S3UploadUtil;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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
    private final CommentRepository commentRepository;

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

    /**
     * 글 목록 조회
     * @return
     */
    public Map<String, Object> findAllDesc() {
        Map<String, Object> result = new HashMap<>();

        List<BoardListResponseDto> boardList = repository.findAllDesc();
        if (boardList.size() > 0) {
            result.put("resultMsg", "SUCCESS");
            result.put("data", boardList);
            return result;
        } else {
            throw new DMException("아직 글이 존재하지 않습니다.");
        }
    }

    /**
     * 글 검색 목록 조회
     * @param title
     * @return
     */
    public Map<String, Object> findByTitle(String title) {
        if (title == "") {
            throw new DMException("검색어를 입력해주세요.");
        }

        Map<String, Object> result = new HashMap<>();

        List<BoardListResponseDto> boardList = repository.findAllByTitle(title);
        if (boardList.size() > 0) {
            result.put("resultMsg", "SUCCESS");
            result.put("data", boardList);
            return result;
        } else {
            throw new DMException(title + "에 대한 검색 결과가 없습니다.");
        }
    }

    /**
     * 상세 조회
     * @param boardSeq
     * @return
     */
    public Map<String, Object> findById(Long boardSeq) {
        Map<String, Object> result = new HashMap<>();

        Optional<BoardResponseDto> board = repository.findByBoardSeq(boardSeq);
        List<CommentDto> commentList = commentRepository.findAllByBoardSeq(boardSeq);

        if (board.isPresent()) {
            /** 조회수 증가 */
            saveReadCount(board.get());
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("board", board.get());
            dataMap.put("commentList", commentList);
            result.put("data", dataMap);
            result.put("resultMsg", "SUCCESS");
            return result;
        } else {
            throw new DMException("해당 게시글이 없습니다.");
        }
    }
    /** 조회수 증가 */
    @Transactional
    protected void saveReadCount(BoardResponseDto dto) {
        Board board = repository.findById(dto.getBoardSeq()).get();
        board.setSeq(board.getSeq());
        board.setReadCount(board.getReadCount() + 1);
        repository.save(board);
    }

    /**
     * 글 등록
     * @param dto
     * @return
     */
    @Transactional
    public Map<String, Object> save(BoardSaveRequestDto dto, Authentication authentication) {
        Long userSeq = ((User) authentication.getPrincipal()).getSeq();
        dto.setUserSeq(userSeq);
        Board board = repository.save(dto.toEntity());
        if (board.getSeq() > 0) {
            return MessageUtil.setResultMsg(Message.성공);
        } else {
            throw new DMException("게시글 등록 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    /**
     * 글 수정
     * @param boardSeq
     * @param dto
     * @return
     */
    @Transactional
    public Map<String, Object> update(Long boardSeq, BoardUpdateRequestDto dto, Authentication authentication) {

        Board board = repository.findById(boardSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        Long userSeq = ((User) authentication.getPrincipal()).getSeq();
        if (board.getWriter() != userSeq) {
            throw new DMException("로그인한 사용자와 작성자가 일치하지 않습니다.");
        }

        board.update(dto.getWebtoonSeq(), dto.getTitle(), dto.getContents());
        repository.save(board);
        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 글 삭제
     * @param boardSeq
     * @return
     */
    @Transactional
    public Map<String, Object> delete(Long boardSeq) {
        Board board = repository.findById(boardSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        repository.delete(board);
        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 좋아요 등록/삭제
     * @param dto
     * @return
     */
    @Transactional
    public Map<String, Object> saveLike(LikeRequestDto dto, Authentication authentication) {
        Long userSeq = ((User) authentication.getPrincipal()).getSeq();
        dto.setUserSeq(userSeq);
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