package com.example.cookie.board.domain.dto;

import com.example.cookie.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {

    private Long webtoonSeq;
    private String title;
    private String contents;
    private Long userSeq;

    @Builder
    public BoardSaveRequestDto(Long webtoonSeq, String title, String contents, Long userSeq) {
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
