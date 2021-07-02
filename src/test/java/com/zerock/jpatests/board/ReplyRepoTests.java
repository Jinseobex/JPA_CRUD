package com.zerock.jpatests.board;

import com.zerock.jpatests.board.entity.Board;
import com.zerock.jpatests.board.entity.Reply;
import com.zerock.jpatests.board.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class ReplyRepoTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsert() {

            IntStream.rangeClosed(1, 1000).forEach(i -> {
                long bno = (int)(Math.random() * 200 + 1);

                Board board = Board.builder().bno(bno).build();

                Reply reply = Reply.builder()
                        .replyText("야호")
                        .board(board)
                        .build();

                replyRepository.save(reply);
            });
    }

    @Test
    public void testRead() {

        Optional<Reply> result = replyRepository.findById(2L);

        log.info(result);

        result.ifPresent(reply -> {
            log.info(reply);
        });

    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(reply -> {
            log.info(reply);
            log.info("===============================");
        });

        // 쿼리가 3번 날아간다. 뭔가 이상하다.
        // 겨우 3번 날아가는데 왜 그럴까?  라고 생각하면 안된다!
        // 시스템
    }

    @Test
    public void testMyBoard() {
        Board board = Board.builder()
                .bno(3L)
                .build();
        Pageable pageable = PageRequest.of(0, 10); // 없는 페이지 가져오면 안 뜸
        replyRepository.getByBoard(board, pageable).getContent().forEach(reply -> {
            log.info(reply);
        });
    }

}
