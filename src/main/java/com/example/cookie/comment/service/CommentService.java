package com.example.cookie.comment.service;

import com.example.cookie.comment.domain.Comment;
import com.example.cookie.comment.domain.CommentFormData;
import com.example.cookie.comment.repository.CommentRepository;
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
     * @param formData
     * @return
     */
    @Transactional
    public Map<String, Object> insertComment(CommentFormData formData) {
        Map<String, Object> result = new HashMap<>();

        // 1. TODO 이미지 파일이 있으면 파일을 업로드 한다.
        MultipartFile imageFile = formData.getImage();

        // 2. 댓글 내용 등록
        Comment comment = new Comment();
        comment.setBoardSeq(formData.getBoardSeq());
        comment.setContents(formData.getContents());
        if (formData.getParentSeq() != null) {
            comment.setParentSeq(formData.getParentSeq());
        }
        repository.save(comment);

        return result;
    }

    /**
     * 댓글 수정
     * @param formData
     * @return
     */
    @Transactional
    public Map<String, Object> updateComment(CommentFormData formData) {
        Map<String, Object> result = new HashMap<>();

        // 기존 댓글 조회
        Long commentSeq = formData.getCommentSeq();
        Optional<Comment> byId = repository.findById(commentSeq);

        if (byId.isEmpty()) {
            // TODO 수정할 댓글이 존재하지 않는다는 오류 리턴
        }

        // TODO 기존 이미지 삭제

        // TODO 새로운 이미지가 있다면 등록
        MultipartFile imageFile = formData.getImage();
        if (!imageFile.isEmpty()) {

        }

        // 댓글 수정
        Comment comment = byId.get();
        comment.setContents(formData.getContents());
        repository.save(comment);

        return result;
    }

    /**
     * 댓글 삭제
     * @param commentSeq
     * @return
     */
    @Transactional
    public Map<String, Object> deleteComment(Long commentSeq) {
        repository.deleteById(commentSeq);
        Optional<Comment> byId = repository.findById(commentSeq);
        if (byId.isPresent()) {
            return MessageUtil.setResultMsg(Message.댓글삭제오류);
        }
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
