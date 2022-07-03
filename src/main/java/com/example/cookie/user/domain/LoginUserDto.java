package com.example.cookie.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUserDto {

    private Object data;
    private String resultMsg;
    private String jwtToken;
}
