package com.example.cookie.oauth.dto;

import com.example.cookie.common.Role;
import com.example.cookie.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String registrationId;
    private String id;
    private String profileImage;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String registrationId, String id, String profileImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.registrationId = registrationId;
        this.id = id;
        this.profileImage = profileImage;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver(userNameAttributeName, registrationId, attributes);
        } else {
            return ofKakao(userNameAttributeName, registrationId, attributes);
        }
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, String registrationId, Map<String, Object> attributes) {
        log.info("attributes: {}", attributes);
        log.info("userNameAttributeName: {}", userNameAttributeName);
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        return OAuthAttributes.builder()
                .id(String.valueOf((Long) attributes.get("id")))
                .profileImage((String) properties.get("profile_image"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, String registrationId, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .id((String) response.get("id"))
                .profileImage((String) response.get("profile_image"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .profileImage(profileImage)
                .platform(registrationId)
                .role(String.valueOf(Role.USER))
                .joinDate(LocalDate.now())
                .isLeave(false)
                .build();
    }
}