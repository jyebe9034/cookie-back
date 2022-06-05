package com.example.cookie.webtoon.repository;

import com.example.cookie.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

    Optional<Webtoon> findByTitle(String title);

    List<Webtoon> findAllByGenre(String taste);
}
