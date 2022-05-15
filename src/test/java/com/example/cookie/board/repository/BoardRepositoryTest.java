package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void saveAndFind() {
        // given
        Long webtoonSeq = 40L;
        String title = "제목 테스트";
        String contents = "내용 테스트";
        boardRepository.save(Board.builder()
                        .webtoonSeq(webtoonSeq)
                        .title(title)
                        .contents(contents)
                        .writer(20L)
                        .build());

        // when
        List<Board> boards = boardRepository.findAll();

        // then
        Board board = boards.get(0);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContents()).isEqualTo(contents);
    }
}