package com.example.cookie.board.controller;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.domain.BoardSaveRequestDto;
import com.example.cookie.board.domain.BoardUpdateRequestDto;
import com.example.cookie.board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private BoardRepository repository;

    @Test
    public void save() throws Exception {
        // given
        String title = "액션 좋아하세요? 겟백 어떠세요?";
        String contents = "겟백 제가 참 좋아하는데요. 같이 보면 더 좋을 것 같아요!";
        BoardSaveRequestDto dto = BoardSaveRequestDto.builder()
                .webtoonSeq(236L)
                .title(title)
                .contents(contents)
                .userSeq(20L)
                .build();

        String url = "http://localhost:" + port + "/api/board";

        // when
        ResponseEntity<Long> responseEntity = template.postForEntity(url, dto, Long.class);

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Board> boards = repository.findAll();
//        assertThat(boards.get(0).getTitle()).isEqualTo(title);
//        assertThat(boards.get(0).getContents()).isEqualTo(contents);
    }

    @Test
    public void update() {
        // given
        Board savedBoard = repository.save(Board.builder()
                .webtoonSeq(50L)
                .title("title")
                .contents("contents")
                .writer(20L)
                .build());

        Long updateId = savedBoard.getSeq();
        String expectedTitle = "title2";
        String expectedContents = "contents2";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                .webtoonSeq(60L)
                .title(expectedTitle)
                .contents(expectedContents)
                .build();

        String url = "http://localhost:" + port + "/api/board/" + updateId;

        HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = template.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> boards = repository.findAll();
        assertThat(boards.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(boards.get(0).getContents()).isEqualTo(expectedContents);
    }
}