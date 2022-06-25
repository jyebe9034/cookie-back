package com.example.cookie.board.domain;

import com.example.cookie.common.BaseDomain;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SequenceGenerator(
        name = "BOARD_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName = "SEQ_BOARD", //시퀀스 이름
        initialValue = 1, //시작값
        allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
)
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "board")
@Data
@Entity
public class Board extends BaseDomain {

    @Id
    @Column(name = "board_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GEN")
    private Long seq;

    @Column(nullable = false)
    private Long webtoonSeq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int readCount;

    @Builder
    public Board(Long webtoonSeq, String title, String contents, Long writer) {
        this.webtoonSeq = webtoonSeq;
        this.title = title;
        this.contents = contents;
        this.setWriter(writer);
    }

    public void update(Long webtoonSeq, String title, String contents) {
        this.webtoonSeq = webtoonSeq;
        this.title = title;
        this.contents = contents;
    }
}
