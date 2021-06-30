package com.zerock.jpatests.board.repository;

import com.zerock.jpatests.board.entity.Board;
import com.zerock.jpatests.board.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> getByBoard(Board board, Pageable pageable);

}
