package com.example.cookie.oauth.dto;

import com.example.cookie.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SessionUser {

    private Long seq;
    private String id;
    private String nickname;
    private String jwtToken;

    public SessionUser(User user) {
        this.seq = user.getSeq();
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.jwtToken = user.getJwtToken();
    }
}
