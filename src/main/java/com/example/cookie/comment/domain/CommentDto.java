package com.example.cookie.comment.domain;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentDto {

    private Long commentSeq;
    private Long boardSeq;
    private Long parentSeq;
    private String contents;
    private String nickName;
    private String profileImage;
    private Long writer;
    private String createDate;

    public CommentDto(Comment comment, String nickName, String profileImage) {
        this.commentSeq = comment.getCommentSeq();
        this.boardSeq = comment.getBoardSeq();
        this.parentSeq = comment.getParentSeq();
        this.contents = comment.getContents();
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.writer = comment.getWriter();
        this.createDate = comment.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
