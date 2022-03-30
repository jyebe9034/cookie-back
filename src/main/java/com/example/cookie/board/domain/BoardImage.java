package com.example.cookie.board.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "board_image")
@Data
@Entity
public class BoardImage {

    @Id
    @Column(name = "image_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private Long boardSeq;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;
}
