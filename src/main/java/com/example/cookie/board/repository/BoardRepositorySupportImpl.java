package com.example.cookie.board.repository;

import com.example.cookie.board.domain.*;
import com.example.cookie.board.domain.dto.BoardListResponseDto;
import com.example.cookie.board.domain.dto.BoardResponseDto;
import com.example.cookie.comment.domain.QComment;
import com.example.cookie.user.domain.QUser;
import com.example.cookie.webtoon.domain.QWebtoon;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositorySupportImpl implements BoardRepositorySupport {

    private final JPAQueryFactory factory;

    QBoard board = QBoard.board;
    QLiked liked = QLiked.liked;
    QComment comment = QComment.comment;
    QUser user = QUser.user;
    QWebtoon webtoon = QWebtoon.webtoon;

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

    @Override
    public List<BoardListResponseDto> findAllDesc() {
        return factory.select(
                        Projections.constructor(
                                BoardListResponseDto.class,
                                board, user, liked.count(), comment.count(), webtoon)
                )
                .from(board)
                .innerJoin(user).on(user.seq.eq(board.writer)).groupBy(user.seq)
                .innerJoin(webtoon).on(webtoon.webtoonSeq.eq(board.webtoonSeq)).groupBy(webtoon.webtoonSeq)
                .leftJoin(liked).on(liked.boardSeq.eq(board.seq)).groupBy(board.seq)
                .leftJoin(comment).on(comment.boardSeq.eq(board.seq)).groupBy(board.seq)
                .fetch();
    }

    @Override
    public List<BoardListResponseDto> findAllByTitle(String title) {
        return factory.select(
                        Projections.constructor(
                                BoardListResponseDto.class,
                                board, user, liked.count(), comment.count(), webtoon)
                )
                .from(board)
                .innerJoin(user).on(user.seq.eq(board.writer)).groupBy(user.seq)
                .innerJoin(webtoon).on(webtoon.webtoonSeq.eq(board.webtoonSeq)).groupBy(webtoon.webtoonSeq)
                .leftJoin(liked).on(liked.boardSeq.eq(board.seq)).groupBy(board.seq)
                .leftJoin(comment).on(comment.boardSeq.eq(board.seq)).groupBy(board.seq)
                .where(board.title.contains(title))
                .orderBy(board.createDate.desc(), board.seq.desc())
                .fetch();
    }

    @Override
    public Optional<BoardResponseDto> findByBoardSeq(Long boardSeq) {
        return Optional.ofNullable(factory.select(
                        Projections.constructor(
                                BoardResponseDto.class,
                                board, user, liked.count(), comment.count(), webtoon)
                )
                .from(board)
                .innerJoin(user).on(user.seq.eq(board.writer)).groupBy(user.seq)
                .innerJoin(webtoon).on(webtoon.webtoonSeq.eq(board.webtoonSeq)).groupBy(webtoon.webtoonSeq)
                .leftJoin(liked).on(liked.boardSeq.eq(board.seq)).groupBy(board.seq)
                .leftJoin(comment).on(comment.boardSeq.eq(board.seq)).groupBy(board.seq)
                .where(board.seq.eq(boardSeq))
                .fetchOne());
    }

    /**
     * 인기달글 5개 조회
     * @return
     */
    @Override
    public List<Board> selectBestBoardList() {
        return factory.selectFrom(board).groupBy(board.seq)
                .leftJoin(liked).on(liked.boardSeq.eq(board.seq)).groupBy(liked.boardSeq)
                .leftJoin(comment).on(comment.boardSeq.eq(board.seq)).groupBy(comment.boardSeq)
                .orderBy(
                        liked.boardSeq.count().desc(),
                        board.readCount.desc(),
                        comment.boardSeq.count().desc()).fetch();
    }
}