package com.example.cookie.board.repository;

import com.example.cookie.board.domain.Board;
import com.example.cookie.board.domain.QBoard;
import com.example.cookie.board.domain.QLiked;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositorySupportImpl implements BoardRepositorySupport {

    private final JPAQueryFactory factory;

    QBoard board = QBoard.board;
    QLiked liked = QLiked.liked;

    /**
     * 좋아요한 글 목록 조회
     * @param userSeq
     * @return
     */
    @Override
    public List<Board> selectMyLikedList(Long userSeq) {
        return factory.select(board)
                .from(board)
                .leftJoin(liked).on(board.seq.eq(liked.boardSeq))
                .where(liked.userSeq.eq(userSeq)).fetch();
    }
}
