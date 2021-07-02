package com.zerock.jpatests.board;

import com.zerock.jpatests.board.entity.Board;
import com.zerock.jpatests.board.entity.Favorite;
import com.zerock.jpatests.board.repository.BoardRepository;
import com.zerock.jpatests.board.repository.FavoriteRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class BoardRepoTests {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Test
    public void testFavorite() {

        IntStream.rangeClosed(1, 500).forEach(i -> {

            long bno = (long)(Math.random() * 200) + 1;
            Board board = Board.builder().bno(bno).build();
            Favorite favorite = Favorite.builder()
                    .board(board)
                    .mark(true)
                    .actor("user00")
                    .build();

            favoriteRepository.save(favorite);
        });

    }

    @Test
    public void testGet1() {

        Pageable pageable = PageRequest.of(0, 10);

        //object[] 오브젝트 배열로 나오는 것 확실히 기억할 것
        Page<Object[]> result = boardRepository.getData1(pageable);

        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));

    }

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

    @Test
    public void testSearch() {
        Pageable pageable = PageRequest.of(1, 10);
        String type = "tcw";
        String keyword = "10";

        Page<Object[]> result = boardRepository.getSearchList(type, keyword, pageable);

    }

}
