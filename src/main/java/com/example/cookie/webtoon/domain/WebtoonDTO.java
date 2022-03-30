package com.example.cookie.webtoon.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebtoonDTO {

    private Long webtoonSeq;

    private String title;

    private String writer;

    private String genre;

    private String thumbnail;

    private String link;

    private String platform;

    private int age;

    @QueryProjection
    public WebtoonDTO(Long webtoonSeq, String title, String writer, String genre, String thumbnail, String link, String platform, int age) {
        this.webtoonSeq = webtoonSeq;
        this.title = title;
        this.writer = writer;
        this.genre = genre;
        this.thumbnail = thumbnail;
        this.link = link;
        this.platform = platform;
        this.age = age;
    }
}
