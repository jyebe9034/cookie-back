package com.example.cookie.notice.domain;

import com.example.cookie.common.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Table(name = "notice")
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Notice extends BaseDomain {

    @Id
    @Column(name = "notice_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    private Long modifier;

    private Date updateDate;
}
