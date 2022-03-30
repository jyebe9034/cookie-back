package com.example.cookie.comment.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CommentImage {

    @Id
    private Long seq;
}
