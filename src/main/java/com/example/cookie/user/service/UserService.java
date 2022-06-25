package com.example.cookie.user.service;

import com.example.cookie.exception.DMException;
import com.example.cookie.oauth.dto.SessionUser;
import com.example.cookie.security.jwt.JwtTokenProvider;
import com.example.cookie.user.domain.User;
import com.example.cookie.user.domain.UserDto;
import com.example.cookie.user.repository.UserRepository;
import com.example.cookie.util.message.Message;
import com.example.cookie.util.message.MessageUtil;
import com.example.cookie.webtoon.domain.Webtoon;
import com.example.cookie.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService{

    private final UserRepository repository;
    private final WebtoonRepository webtoonRepository;
    private final JwtTokenProvider tokenProvider;

    /**
     * 회원가입 추가 정보
     * @param dto
     * @return
     */
    @Transactional
    public Map<String, Object> join(UserDto dto) {
        Map<String, Object> result = new HashMap<>();

        User user = repository.findById(dto.getSeq()).get();
        user.setNickname(dto.getNickname());
        user.setTaste(dto.getTaste());
        repository.save(user);

        if (user.getNickname() != null) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("seq", user.getSeq());
            dataMap.put("id", user.getId());
            dataMap.put("nickname", user.getNickname());
            dataMap.put("role", user.getRole());

            result.put("resultMsg", "SUCCESS");
            result.put("user", dataMap);
            result.put("jwt-token", user.getJwtToken());
            return result;
        } else {
            throw new DMException("추가 정보 등록 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    /**
     * 로그인 (토큰 제공)
     * @param sessionUser
     * @return
     */
    @Transactional
    public Map<String, Object> login(SessionUser sessionUser) {
        Map<String, Object> result = new HashMap<>();

        String token = tokenProvider.createToken(sessionUser.getId());

        User user = repository.findById(sessionUser.getSeq()).get();
        user.setJwtToken(token);
        repository.save(user);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("seq", user.getSeq());
        dataMap.put("id", user.getId());
        dataMap.put("nickname", user.getNickname());
        dataMap.put("role", user.getRole());

        result.put("resultMsg", "SUCCESS");
        result.put("user", dataMap);
        result.put("jwt-token", token);
        return result;
    }

    /**
     * 로그아웃
     * @param userSeq
     * @return
     */
    @Transactional
    public Map<String, Object> logout(Long userSeq) {
        User user = repository.findById(userSeq).get();
        user.setJwtToken(null);
        repository.save(user);

        if (user.getJwtToken() == null) {
            return MessageUtil.setResultMsg(Message.성공);
        } else {
            throw new DMException("로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
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
        throw new DMException("내 정보 수정 중 문제가 발생했습니다.");
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

    /**
     * 추천 웹툰 랜덤 선정
     * @param taste
     * @return
     */
    public int[] makeRecommendWebtoonSeq(String[] taste) {
        int count = taste.length;
        if (count == 1) { // 3개만 추천
            int[] result = new int[3];
            List<Webtoon> allByGenre = webtoonRepository.findAllByGenre(taste[0]);
            for (int i = 0; i < 3; i++) {
                result = calculateRecommendWebtoon(result, allByGenre, i);
            }
            return result;
        } else if (count == 2) { // 4개 추천
            int[] result = new int[4];
            for (int i = 0; i < count; i++) {
                List<Webtoon> allByGenre = webtoonRepository.findAllByGenre(taste[i]);
                for (int j = 0; j < 2; j++) {
                    result = calculateRecommendWebtoon(result, allByGenre, j);
                }
            }
            return result;
        } else { // 5개 추천
            int[] result = new int[5];
            for (int i = 0; i < count; i++) {
                List<Webtoon> allByGenre = webtoonRepository.findAllByGenre(taste[i]);
                if (i == 0) {
                    for (int j = 0; j < 2; j++) {
                        result = calculateRecommendWebtoon(result, allByGenre, j);
                    }
                } else if (i == 1) {
                    for (int j = 2; j < 4; j++) {
                        result = calculateRecommendWebtoon(result, allByGenre, j);
                    }
                } else {
                    result = calculateRecommendWebtoon(result, allByGenre, 4);
                }
            }
            return result;
        }
    }

    /**
     * 추천 웹툰 랜덤 선정 계산
     * @param result
     * @param list
     * @param index
     * @return
     */
    private int[] calculateRecommendWebtoon(int[] result, List<Webtoon> list, int index) {
        int num = (int) (Math.random() * list.size() + 1);
        Webtoon webtoon = list.get(num);
        result[index] = webtoon.getWebtoonSeq().intValue();
        return result;
    }
}
