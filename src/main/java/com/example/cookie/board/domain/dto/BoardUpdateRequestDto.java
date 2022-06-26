package com.example.cookie.board.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {

    private Long webtoonSeq;
    private String title;
    private String contents;

    @Builder
    public BoardUpdateRequestDto(Long webtoonSeq, String title, String contents) {
        this.webtoonSeq = webtoonSeq;
        this.title = title;
        this.contents = contents;
    }
}
