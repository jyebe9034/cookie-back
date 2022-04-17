package com.example.cookie.user.service;

import com.example.cookie.common.Role;
import com.example.cookie.security.JwtTokenProvider;
import com.example.cookie.security.oauth.domain.KakaoProfile;
import com.example.cookie.security.oauth.domain.NaverProfile;
import com.example.cookie.user.domain.User;
import com.example.cookie.user.domain.UserDto;
import com.example.cookie.user.repository.UserRepository;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtTokenProvider tokenProvider;

    public UserService(UserRepository repository, @Lazy JwtTokenProvider tokenProvider) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
    }

    public Map<String, Object> manageLoginOrJoin(ResponseEntity<String> profile, String platform) throws JsonProcessingException {
        Map<String, Object> result = new HashMap<>();

        UserDto userInfo = new UserDto();
        userInfo = platform.equals("Kakao") ? getKakaoUserInfo(profile) : getNaverUserInfo(profile);

        String id = userInfo.getId();

        Optional<User> user = repository.findById(id);
        log.info("user = {}", user);
        result = user.isEmpty() ? join(userInfo, platform) : login(user.get());
        return result;
    }

    private UserDto getKakaoUserInfo(ResponseEntity<String> profile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = objectMapper.readValue(profile.getBody(), KakaoProfile.class);

        UserDto userInfo = new UserDto();
        userInfo.setId(kakaoProfile.getId());
        userInfo.setNickname(kakaoProfile.getProperties().get("nickname").toString());
        //userInfo.setAge(kakaoProfile.getKakao_account().get("age_range").toString());
        userInfo.setProfileImage(kakaoProfile.getProperties().get("profile_image").toString());
        return userInfo;
    }

    private UserDto getNaverUserInfo(ResponseEntity<String> profile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NaverProfile naverProfile = objectMapper.readValue(profile.getBody(), NaverProfile.class);

        UserDto userInfo = new UserDto();
        userInfo.setId(naverProfile.getResponse().get("id").toString());
        userInfo.setNickname(naverProfile.getResponse().get("nickname").toString());
        userInfo.setAge(naverProfile.getResponse().get("age").toString());
        userInfo.setProfileImage(naverProfile.getResponse().get("profile_image").toString());
        return userInfo;
    }

    @Transactional
    public Map<String, Object> join(UserDto userInfo, String platform) {
        Map<String, Object> result = new HashMap<>();

        log.info("userDto = {}", userInfo);

        User entity = new User();
        if (!userInfo.getId().isEmpty()) {
            entity.setId(userInfo.getId());
        }
        if (userInfo.getNickname()!= null) {
            entity.setNickname(userInfo.getNickname());
        }
        if (userInfo.getAge()!= null) {
            entity.setBirthYear(userInfo.getAge());
        } else {
            entity.setBirthYear("1995");
        }
        if (userInfo.getProfileImage() != null) {
            entity.setProfileImage(userInfo.getProfileImage());
        }
        log.info("age = {}", entity.getBirthYear());

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

        result.put("seq", user.getSeq());
        result.put("id", user.getId());
        result.put("nickname", user.getNickname());
        result.put("role", user.getRole());
        result.put("jwt-token", tokenProvider.createToken(user.getId()));
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    /**
     * 내 정보 조회
     * @param userSeq
     * @return
     */
    public User selectMyInfo(Long userSeq) {
        return repository.findById(userSeq).orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
    }

    /**
     * 내 정보 수정
     * @param userSeq
     * @param user
     * @return
     */
    @Transactional
    public Map<String, Object> updateMyInfo(Long userSeq, User user) {
        User originUser = selectMyInfo(userSeq);
        if (user.getNickname() != null && StringUtils.hasText(user.getNickname())) {
            originUser.setNickname(user.getNickname());
        }
        if (user.getTaste() != null && user.getTaste().length > 0) {
            originUser.setTaste(user.getTaste());
        }
        User save = repository.save(originUser);
        if (save.getNickname().equals(user.getNickname()) && save.getTaste() == user.getTaste()) {
            return MessageUtil.setResultMsg(Message.성공);
        }
        return MessageUtil.setResultMsg(Message.수정오류);
    }

    /**
     * 탈퇴
     * @param userSeq
     * @return
     */
    @Transactional
    public Map<String, Object> deleteMyInfo(Long userSeq) {
        User originUser = selectMyInfo(userSeq);
        originUser.setLeave(true);
        User save = repository.save(originUser);
        if (save.isLeave() == false) {
            return MessageUtil.setResultMsg(Message.탈퇴오류);
        }
        return MessageUtil.setResultMsg(Message.성공);
    }
}
