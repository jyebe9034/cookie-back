package com.example.cookie.board.domain.dto;

import com.example.cookie.board.domain.Board;
import com.example.cookie.user.domain.User;
import com.example.cookie.webtoon.domain.Webtoon;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class BoardListResponseDto {

    private Long boardSeq;
    private String thumbnailPath;
    private String title;
    private String nickname;
    private String createDate;
    private int readCount;
    private Long likeCount;
    private Long commentCount;
    private String webtoonLink;
    private String genre;

    public BoardListResponseDto(Board board, User user, Long likeCount, Long commentCount, Webtoon webtoon) {
        this.boardSeq = board.getSeq();
        this.thumbnailPath = webtoon.getThumbnail();
        this.title = board.getTitle();
        this.nickname = user.getNickname();
        this.createDate = board.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.readCount = board.getReadCount();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.webtoonLink = webtoon.getLink();
        this.genre = webtoon.getGenre();
    }
}
