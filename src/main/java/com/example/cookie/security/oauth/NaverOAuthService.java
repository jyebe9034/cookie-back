package com.example.cookie.security.oauth;

import com.example.cookie.security.oauth.domain.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class NaverOAuthService {

    @Value("${oauth.naver.client-id}")
    private String CLIENT_ID;

    @Value("${oauth.naver.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${oauth.naver.client-secret}")
    private String CLIENT_SECRET;

    public MultiValueMap<String, String> getNaverAuthCode(String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", CLIENT_ID);
        map.add("client_secret", CLIENT_SECRET);
        map.add("code", code);

        log.info("requestCode = {}", map);
        return map;
    }

    public ResponseEntity<String> getNaverToken(HttpEntity httpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
          "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                httpEntity,
                String.class
        );
    }

    public HttpEntity<MultiValueMap<String, String>> requestNaverProfile(String token) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String accessToken = objectMapper.readValue(token, OAuthToken.class).getAccess_token();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        return new HttpEntity<>(headers);
    }

    public ResponseEntity<String> getNaverProfile(HttpEntity entity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
          "https://openapi.naver.com/v1/nid/me",
              HttpMethod.POST,
              entity,
              String.class
        );
    }
}
