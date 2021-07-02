package com.zerock.jpatests.board.repository;

import com.zerock.jpatests.board.entity.Board;
import com.zerock.jpatests.board.repository.dynamic.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query("select b, count(distinct r), count(distinct f) from Board b " +
            "left join Reply r on r.board = b " +
            "left join Favorite f on f.board = b " +
            "group by b order by b.bno desc ")
    Page<Object[]> getData1(Pageable pageable);


}
