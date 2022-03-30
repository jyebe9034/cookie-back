package com.example.cookie.webtoon.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "webtoon")
@SequenceGenerator(
        name="WEBTOON_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="SEQ_WEBTOON", //시퀀스 이름
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBTOON_SEQ_GEN")
    private Long webtoonSeq;

    private String title;

    private String writer;

    private String genre;

    private String thumbnail;

    private String link;

    private String platform;

    private Integer age;
}
