package com.example.cookie.user.domain;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

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
public class User {

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
}
