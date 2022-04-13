package com.example.cookie.user.domain;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
@Table(name = "user", schema = "public")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @Column(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String platform;

    @Column(nullable = false, unique = true)
    private String birthYear;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "taste", columnDefinition = "text[]")
    @Type(type = "string-array")
    private String[] taste;

    @Column(length = 4)
    private String mbti;

    @Column(nullable = false, unique = true)
    private String profileImage;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private LocalDate joinDate;

    @Column(nullable = false)
    private boolean isLeave;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return id;
    }

    @Override
    public String getUsername() {
        return id;
    }

    /**
     * 계정 만료
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 인증 정보 만료
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 활성화
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
