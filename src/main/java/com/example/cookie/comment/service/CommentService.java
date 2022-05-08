package com.example.cookie.comment.service;

import com.example.cookie.comment.domain.Comment;
import com.example.cookie.comment.domain.CommentFormData;
import com.example.cookie.comment.domain.CommentImage;
import com.example.cookie.comment.repository.CommentImageRepository;
import com.example.cookie.comment.repository.CommentRepository;
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

    @Value("${spring.file-upload.base-dir}")
    private String BASE_DIR;

    private String rootPath = "/comment";

    private final CommentRepository repository;
    private final CommentImageRepository imageRepository;

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
    public Map<String, Object> insertComment(CommentFormData formData) throws IOException {
        // 1. 댓글 내용 등록
        Comment comment = new Comment();
        comment.setBoardSeq(formData.getBoardSeq());

        // TODO 댓글 내용은 없고 이미지만 있으면 어떻게 하지..?
        comment.setContents(formData.getContents());
        if (formData.getParentSeq() != null) {
            comment.setParentSeq(formData.getParentSeq());
        }
        Comment saveComment = repository.save(comment);

        // 혹시 폴더가 없는 경우에 대비해서 comment폴더 생성
        File commentPath = new File(BASE_DIR + File.separator + rootPath);
        if (!commentPath.exists()) {
            commentPath.mkdir();
        }

        // 2. 이미지 파일이 있으면 파일을 업로드 한다.
        if (!formData.getFile().isEmpty()) {
            String originFileName = formData.getFile().getOriginalFilename();
            String fileName = "";
            String fileExt = originFileName.substring(originFileName.lastIndexOf("."));
            File imageFile = new File(commentPath + File.separator + originFileName);
            if(imageFile.exists()) {
                // 파일이 존재하는 경우 Count 증가시키면서 파일명 변경해서 저장함
                int existCount = 0;
                while(imageFile.exists()) {
                    existCount++;
                    fileName = originFileName.substring(0, originFileName.lastIndexOf(".")) + "_" + existCount + fileExt;
                    imageFile = new File(BASE_DIR + File.separator + fileName);
                }
            } else {
                fileName = originFileName;
            }
            formData.getFile().transferTo(imageFile);

            CommentImage commentImage = new CommentImage();
            commentImage.setComment(saveComment);
            commentImage.setName(fileName);
            commentImage.setPath(imageFile.getPath());
            imageRepository.save(commentImage);
        }

        return MessageUtil.setResultMsg(Message.성공);
    }

    /**
     * 댓글 수정
     * @param formData
     * @return
     */
    @Transactional
    public Map<String, Object> updateComment(CommentFormData formData) throws IOException {
        // 기존 댓글 조회
        Long commentSeq = formData.getCommentSeq();
        Optional<Comment> byId = repository.findById(commentSeq);

        // 기존 댓글이 존재하지 않는 경우
        if (byId.isEmpty()) {
            return MessageUtil.setResultMsg(Message.댓글부재오류);
        }

        // 댓글 수정
        Comment comment = byId.get();
        comment.setContents(formData.getContents());
        Comment saveComment = repository.save(comment);

        // 새로운 이미지가 있다면 등록
        if (!formData.getFile().isEmpty()) {
            // 기존 이미지 삭제
            imageRepository.deleteById(comment.getCommentImage().getCommentImageSeq());

            File commentPath = new File(BASE_DIR + File.separator + rootPath);
            String originFileName = formData.getFile().getOriginalFilename();
            String fileName = "";
            String fileExt = originFileName.substring(originFileName.lastIndexOf("."));
            File imageFile = new File(commentPath + File.separator + originFileName);
            if(imageFile.exists()) {
                // 파일이 존재하는 경우 Count 증가시키면서 파일명 변경해서 저장함
                int existCount = 0;
                while(imageFile.exists()) {
                    existCount++;
                    fileName = originFileName.substring(0, originFileName.lastIndexOf(".")) + "_" + existCount + fileExt;
                    imageFile = new File(BASE_DIR + File.separator + fileName);
                }
            } else {
                fileName = originFileName;
            }
            formData.getFile().transferTo(imageFile);

            CommentImage commentImage = new CommentImage();
            commentImage.setComment(saveComment);
            commentImage.setName(fileName);
            commentImage.setPath(imageFile.getPath());
            imageRepository.save(commentImage);
        }

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
            return MessageUtil.setResultMsg(Message.댓글부재오류);
        }
        Comment comment = byId.get();

        // 댓글 이미지 삭제
        imageRepository.deleteById(comment.getCommentImage().getCommentImageSeq());

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
