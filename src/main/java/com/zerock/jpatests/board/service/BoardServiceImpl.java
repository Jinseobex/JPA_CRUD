package com.zerock.jpatests.board.service;

import com.zerock.jpatests.board.dto.BoardListRequestDTO;
import com.zerock.jpatests.board.dto.ListBoardDTO;
import com.zerock.jpatests.board.repository.BoardRepository;
import com.zerock.jpatests.common.dto.ListResponseDto;
import com.zerock.jpatests.common.dto.PageMaker;
import com.zerock.jpatests.todo.dto.TodoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public ListResponseDto<ListBoardDTO> getList(BoardListRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable();

        Page<Object[]> result = boardRepository.getSearchList(requestDTO.getType(), requestDTO.getKeyword(), pageable);

        List<ListBoardDTO> boardDTOList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(requestDTO.getPage(),requestDTO.getSize(),(int)result.getTotalElements());

        log.info(result);

        return ListResponseDto.<ListBoardDTO>builder()
                .dtoList(boardDTOList)
                .pageMaker(pageMaker)
                .listRequestDTO(null)
                .build();

    }

}
