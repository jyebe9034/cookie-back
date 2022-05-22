package com.example.cookie.board.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@SequenceGenerator(
        name = "LIKED_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName = "SEQ_LIKED", //시퀀스 이름
        initialValue = 1, //시작값
        allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
)
@NoArgsConstructor
@Table(name = "liked")
@Data
@Entity
public class Liked {

    @Id
    @Column(name = "liked_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKED_SEQ_GEN")
    private Long seq;

    @Column(nullable = false)
    private Long boardSeq;

    @Column(nullable = false)
    private Long userSeq;

    @Column(nullable = false)
    private LocalDate createDate;

    @Builder
    public Liked(Long boardSeq, Long userSeq, Date createDate) {
        this.boardSeq = boardSeq;
        this.userSeq = userSeq;
        this.createDate = LocalDate.now();
    }
}
