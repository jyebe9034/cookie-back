package com.example.cookie.comment.domain;

import lombok.Data;

@Data
public class CommentDto extends Comment {

    private String nickName;

    private String profileImage;
}
