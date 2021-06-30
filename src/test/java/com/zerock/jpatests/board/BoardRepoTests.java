package com.zerock.jpatests.board;

import com.zerock.jpatests.board.entity.Board;
import com.zerock.jpatests.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class BoardRepoTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 200).forEach(i -> {
            Board board = Board.builder()
                    .title("제목")
                    .content("내용")
                    .writer("user00")
                    .build();
            boardRepository.save(board);

            log.info(board);
        });

    }

}
