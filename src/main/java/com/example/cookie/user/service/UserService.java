package com.example.cookie.user.service;

import com.example.cookie.common.Role;
import com.example.cookie.security.JwtTokenProvider;
import com.example.cookie.security.oauth.domain.KakaoProfile;
import com.example.cookie.security.oauth.domain.NaverProfile;
import com.example.cookie.user.domain.User;
import com.example.cookie.user.domain.UserDto;
import com.example.cookie.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService{

    private final UserRepository repository;
    private final JwtTokenProvider tokenProvider;

    public Map<String, Object> manageLoginOrJoin(ResponseEntity<String> profile, String platform) throws JsonProcessingException {
        Map<String, Object> result = new HashMap<>();

        UserDto userInfo = new UserDto();
        userInfo = platform.equals("Kakao") ? getKakaoUserInfo(profile) : getNaverUserInfo(profile);

        String id = userInfo.getId();

        Optional<User> user = repository.findById(id);
        result = user.isEmpty() ? join(userInfo, platform) : login(user.get());
        return result;
    }

    private UserDto getKakaoUserInfo(ResponseEntity<String> profile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = objectMapper.readValue(profile.getBody(), KakaoProfile.class);

        UserDto userInfo = new UserDto();
        userInfo.setId(kakaoProfile.getId());
        userInfo.setName(kakaoProfile.getProperties().get("nickname").toString());
        userInfo.setProfileImage(kakaoProfile.getProperties().get("profile_image").toString());
        return userInfo;
    }

    private UserDto getNaverUserInfo(ResponseEntity<String> profile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NaverProfile naverProfile = objectMapper.readValue(profile.getBody(), NaverProfile.class);

        UserDto userInfo = new UserDto();
        userInfo.setId(naverProfile.getResponse().get("id").toString());
        userInfo.setName(naverProfile.getResponse().get("name").toString());
        userInfo.setProfileImage(naverProfile.getResponse().get("profile_image").toString());
        return userInfo;
    }

    @Transactional
    public Map<String, Object> join(UserDto userInfo, String platform) {
        Map<String, Object> result = new HashMap<>();

        User entity = new User();
        if (!userInfo.getId().isEmpty()) {
            entity.setId(userInfo.getId());
        }
        if (userInfo.getName()!= null) {
            entity.setName(userInfo.getName());
        }
        if (userInfo.getNickname()!= null) {
            entity.setNickname(userInfo.getNickname());
        }
        if (userInfo.getProfileImage() != null) {
            entity.setProfileImage(userInfo.getProfileImage());
        }
        String[] taste = {"test"};

        entity.setPlatform(platform);
        entity.setTaste(taste);
        entity.setRole(Role.USER.toString());
        entity.setJoinDate(LocalDate.now());
        entity.setLeave(false);

        User save = repository.save(entity);

        result.put("isSuccess", "true");
        result.put("seq", save.getSeq());
        return result;
    }

    public Map<String, Object> login(User user) {
        Map<String, Object> result = new HashMap<>();

        String token = tokenProvider.createToken(user.getId());
        user.setJwtToken(token);
        repository.save(user);

        result.put("seq", user.getSeq());
        result.put("id", user.getId());
        result.put("nickname", user.getNickname());
        result.put("role", user.getRole());
        result.put("jwt-token", token);
        return result;
    }

    @Transactional
    public void naverLogout(String id) {
        User user = repository.findById(id).get();
        user.setJwtToken(null);
        repository.save(user);
    }
}
