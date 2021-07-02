package com.zerock.jpatests.board.controller;

import com.zerock.jpatests.board.dto.BoardListRequestDTO;
import com.zerock.jpatests.board.dto.ListBoardDTO;
import com.zerock.jpatests.board.service.BoardService;
import com.zerock.jpatests.common.dto.ListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDto<ListBoardDTO>> list(BoardListRequestDTO RequestDTO) {

        return ResponseEntity.ok(boardService.getList(RequestDTO));

    }

}
