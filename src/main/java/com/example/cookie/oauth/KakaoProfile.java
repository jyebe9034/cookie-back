package com.example.cookie.oauth;

import lombok.Data;

@Data
public class KakaoProfile {

    private String id;

    class KakaoAcount {
        class Profile {
            private String nickname;
            private String profile_image_url;
        }

        private String age_range;
    }
}