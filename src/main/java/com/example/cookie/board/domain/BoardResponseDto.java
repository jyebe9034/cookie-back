package com.example.cookie.board.domain;

import com.example.cookie.user.domain.User;
import com.example.cookie.webtoon.domain.Webtoon;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long boardSeq;
    private String thumbnailPath;
    private String title;
    private String contents;
    private Long writer;
    private String nickname;
    private String createDate;
    private int readCount;
    private int likeCount;
    private int commentCount;
    private String genre;

    public BoardResponseDto(Board board, User user, int likeCount, int commentCount, Webtoon webtoon) {
        this.boardSeq = board.getSeq();
        this.thumbnailPath = webtoon.getThumbnail();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.writer = board.getWriter();
        this.nickname = user.getNickname();
        this.createDate = String.valueOf(board.getCreateDate());
        this.readCount = board.getReadCount();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.genre = webtoon.getGenre();
    }
}
