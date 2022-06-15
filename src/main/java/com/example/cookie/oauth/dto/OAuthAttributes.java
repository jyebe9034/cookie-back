package com.example.cookie.oauth.dto;

import com.example.cookie.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String id;
    private String profileImage;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String id, String profileImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.id = id;
        this.profileImage = profileImage;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver(userNameAttributeName, attributes);
        } else {
            return ofKakao(userNameAttributeName, attributes);
        }
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        return OAuthAttributes.builder()
                .id(String.valueOf((Long) attributes.get("id")))
                .profileImage((String) properties.get("profile_image"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .id((String) attributes.get("id"))
                .profileImage((String) properties.get("profile_image"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .profileImage(profileImage)
                .build();
    }
}