package com.example.cookie.user.service;

import com.example.cookie.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public boolean login(ResponseEntity<String> kakaoProfile) {

        /**
         * TODO kakaoProfile에서 id 가져와서 비교 후 로그인 or 회원가입 처리
         */

        return true;
    }
}
