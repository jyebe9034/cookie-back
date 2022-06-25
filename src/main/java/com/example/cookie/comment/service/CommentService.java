package com.example.cookie.comment.service;

import com.example.cookie.comment.domain.Comment;
import com.example.cookie.comment.repository.CommentRepository;
import com.example.cookie.exception.DMException;
import com.example.cookie.util.S3UploadUtil;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    /**
     * 댓글 조회
     * @param boardSeq
     * @return
     */
    public List<Comment> selectComment(Long boardSeq) {
        return repository.findAllByBoardSeq(boardSeq);
    }

    /**
     * 댓글 등록
     * @param comment
     * @return
     */
    @Transactional
    public Map<String, Object> insertComment(Comment comment) {
        // 댓글 저장
        repository.save(comment);
        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 댓글 수정
     * @param comment
     * @return
     */
    @Transactional
    public Map<String, Object> updateComment(Comment comment) {
        // 기존 댓글 조회
        Long commentSeq = comment.getCommentSeq();
        Optional<Comment> byId = repository.findById(commentSeq);

        // 기존 댓글이 존재하지 않는 경우
        if (byId.isEmpty()) {
            throw new DMException("수정할 댓글이 존재하지 않습니다.");
        }

        // 기존 댓글 조회
        Comment origin = byId.get();
        origin.setContents(comment.getContents());

        // 댓글 수정
        repository.save(origin);
        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 댓글 삭제
     * @param commentSeq
     * @return
     */
    @Transactional
    public Map<String, Object> deleteComment(Long commentSeq) {
        Optional<Comment> byId = repository.findById(commentSeq);
        if (byId.isEmpty()) {
            throw new DMException("삭제할 댓글이 존재하지 않습니다.");
        }

        // 댓글 삭제
        repository.deleteById(commentSeq);
        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 마이페이지 - 내가 작성한 댓글 목록 조회
     * @param userSeq
     * @return
     */
    public List<Comment> selectMyCommentList(Long userSeq) {
        return repository.findAllByWriter(userSeq);
    }
}
