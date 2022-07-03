package com.example.cookie.board.domain.dto;

import com.example.cookie.board.domain.Liked;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class LikeRequestDto {

    private Long boardSeq;
    private Long userSeq;

    public Liked toEntity() {
        return Liked.builder()
                .boardSeq(boardSeq)
                .userSeq(userSeq)
                .build();
    }
}
