package com.example.cookie.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private String id;
    private String name;
    private String nickname;
    private String profileImage;
    private String[] taste;

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .profileImage(profileImage)
                .taste(taste)
                .build();
    }
}
