package com.example.cookie.comment.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "comment_image")
@Data
@Entity
@SequenceGenerator(
        name = "COMMENT_IMAGE_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName = "SEQ_COMMENT_IMAGE", //시퀀스 이름
        initialValue = 1, //시작값
        allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
)
public class CommentImage {

    @Id
    @Column(name = "comment_image_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_IMAGE_SEQ_GEN")
    private Long commentImageSeq;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @OneToOne
    @JoinColumn(name = "commentSeq")
    private Comment comment;
}
