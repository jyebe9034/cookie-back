package com.example.cookie.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user")
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

    /* TODO: 타입 어떻게 처리할지? */
    @Column(nullable = false, unique = true)
    private String taste;

    @Column(length = 4)
    private String mbti;

    @Column(nullable = false, unique = true)
    private String profileImage;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false)
    private Date joinDate;

    @Column(nullable = false)
    private boolean isLeave;
}
