package com.example.cookie.board.domain;

import com.example.cookie.common.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "board")
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Board extends BaseDomain {

    @Id
    @Column(name = "board_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private Long webtoonSeq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int readCount;

}
