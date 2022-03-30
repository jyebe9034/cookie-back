package com.example.cookie.board.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "liked")
@Data
@Entity
public class Liked {

    @Id
    @Column(nullable = false)
    private Long boardSeq;

    @Column(nullable = false)
    private Long userSeq;

    @Column(nullable = false)
    private Date createDate;
}
