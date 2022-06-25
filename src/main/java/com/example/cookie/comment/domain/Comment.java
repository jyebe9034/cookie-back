package com.example.cookie.comment.domain;

import com.example.cookie.common.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "comment")
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@SequenceGenerator(
        name = "COMMENT_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName = "SEQ_COMMENT", //시퀀스 이름
        initialValue = 1, //시작값
        allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
)
public class Comment extends BaseDomain {

    @Id
    @Column(name = "comment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GEN")
    private Long commentSeq;

    @Column(nullable = false)
    private Long boardSeq;

    @Column
    private Long parentSeq;

    @Column(nullable = false)
    private String contents;
}
