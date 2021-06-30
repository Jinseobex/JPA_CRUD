package com.zerock.jpatests.board.repository;

import com.zerock.jpatests.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
