package com.example.cookie.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDomain {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @CreatedBy
    @Column(updatable = false)
    private Long writer;

    @PrePersist
    private void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
    }
}