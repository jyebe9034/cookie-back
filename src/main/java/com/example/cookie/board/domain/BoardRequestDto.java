package com.example.cookie.board.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRequestDto {

    private Long webtoonSeq;

    private String title;

    private String contents;

    private Long userSeq;

    @Builder
    public BoardRequestDto(Long webtoonSeq, String title, String contents) {
        this.webtoonSeq = webtoonSeq;
        this.title = title;
        this.contents = contents;
    }

    @Builder
    public BoardRequestDto(Long webtoonSeq, String title, String contents, Long userSeq) {
        this.webtoonSeq = webtoonSeq;
        this.title = title;
        this.contents = contents;
        this.userSeq = userSeq;
    }

    public Board toEntity() {
        return Board.builder()
                .webtoonSeq(webtoonSeq)
                .title(title)
                .contents(contents)
                .writer(userSeq)
                .build();
    }

}
