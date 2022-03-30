package com.example.cookie.comment.domain;

import com.example.cookie.common.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "comment")
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Comment extends BaseDomain {

    @Id
    @Column(name = "comment_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private Long boardSeq;

    @Column(nullable = false)
    private Long parentSeq;

    @Column(nullable = false)
    private String contents;
}
