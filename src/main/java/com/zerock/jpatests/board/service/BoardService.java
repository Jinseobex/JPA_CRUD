package com.zerock.jpatests.board.service;

import com.zerock.jpatests.board.dto.BoardDTO;
import com.zerock.jpatests.board.dto.BoardListRequestDTO;
import com.zerock.jpatests.board.dto.ListBoardDTO;
import com.zerock.jpatests.board.entity.Board;
import com.zerock.jpatests.common.dto.ListResponseDto;

public interface BoardService {

    ListResponseDto<ListBoardDTO> getList(BoardListRequestDTO RequestDTO);

    default ListBoardDTO arrToDTO(Object[] arr) {

        Board board = (Board)arr[0];
        long replyCount = (long)arr[1];
        long favoriteCount = (long)arr[2];

        return ListBoardDTO.builder()
                .boardDTO(entityToDTO(board))
                .likeCount(favoriteCount)
                .replyCount(replyCount)
                .build();
    }

    default BoardDTO entityToDTO(Board board) {

        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();

    }

}
