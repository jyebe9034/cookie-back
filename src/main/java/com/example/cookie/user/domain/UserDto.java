package com.example.cookie.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private Long seq;
    private String id;
    private String nickname;
    private String[] taste;

    public User toEntity() {
        return User.builder()
                .seq(seq)
                .id(id)
                .nickname(nickname)
                .taste(taste)
                .build();
    }
}
