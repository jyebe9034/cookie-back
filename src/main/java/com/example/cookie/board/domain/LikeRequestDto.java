package com.example.cookie.board.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeRequestDto {

    private Long boardSeq;
    private Long userSeq;

    @Builder
    public LikeRequestDto(Long boardSeq, Long userSeq) {
        this.boardSeq = boardSeq;
        this.userSeq = userSeq;
    }

    public Liked toEntity() {
        return Liked.builder()
                .boardSeq(boardSeq)
                .userSeq(userSeq)
                .build();
    }
}
