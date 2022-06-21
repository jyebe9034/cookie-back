package com.example.cookie.comment.service;

import com.example.cookie.comment.domain.Comment;
import com.example.cookie.comment.domain.CommentFormData;
import com.example.cookie.comment.repository.CommentRepository;
import com.example.cookie.exception.DMException;
import com.example.cookie.util.S3UploadUtil;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final S3UploadUtil s3UploadUtil;
    private String rootPath = "/comment";

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
        // 1. 댓글 내용 등록
        Comment comment = new Comment();
        comment.setBoardSeq(formData.getBoardSeq());

        // TODO 댓글 내용은 없고 이미지만 있으면 어떻게 하지..?
        comment.setContents(formData.getContents());
        if (formData.getParentSeq() != null) {
            comment.setParentSeq(formData.getParentSeq());
        }

        // 2. 이미지 파일이 있으면 파일을 업로드 한다.
        Map<String, Object> uploadResult = s3UploadUtil.upload("comment", formData.getFile());
        comment.setUrl(uploadResult.get("url").toString());
        comment.setFileName(uploadResult.get("fileName").toString());

        // 댓글 저장
        repository.save(comment);

        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 댓글 수정
     * @param formData
     * @return
     */
    @Transactional
    public Map<String, Object> updateComment(CommentFormData formData) {
        // 기존 댓글 조회
        Long commentSeq = formData.getCommentSeq();
        Optional<Comment> byId = repository.findById(commentSeq);

        // 기존 댓글이 존재하지 않는 경우
        if (byId.isEmpty()) {
            throw new DMException("수정할 댓글이 존재하지 않습니다.");
        }

        // 기존 댓글 조회
        Comment comment = byId.get();
        comment.setContents(formData.getContents());

        // 새로운 이미지가 있는 경우
        if (!formData.getFile().isEmpty()) {
            // FIXME 기존 이미지 삭제?
            Map<String, Object> uploadResult = s3UploadUtil.upload("comment", formData.getFile());
            comment.setUrl(uploadResult.get("url").toString());
            comment.setFileName(uploadResult.get("fileName").toString());
        }

        // 댓글 수정
        repository.save(comment);

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
        Comment comment = byId.get();
        // FIXME 댓글 이미지 삭제?

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
