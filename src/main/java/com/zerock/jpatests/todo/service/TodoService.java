package com.zerock.jpatests.todo.service;

import com.zerock.jpatests.common.dto.ListResponseDto;
import com.zerock.jpatests.common.dto.ListRequestDTO;
import com.zerock.jpatests.todo.dto.TodoDTO;
import com.zerock.jpatests.todo.entity.Todo;

public interface TodoService {

    Long register(TodoDTO dto);

    TodoDTO read(Long tno);

    default TodoDTO EntityToDto(Todo entity) {
        return TodoDTO.builder()
                .tno(entity.getTno())
                .content((entity.getContent()))
                .del(entity.isDel())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }

    default Todo dtoToEntity(TodoDTO dto) {

        return Todo.builder()
                .tno(dto.getTno())
                .content(dto.getContent())
                .del(dto.isDel())
                .build();
    }

    Long remove(Long tno);

    TodoDTO modify(TodoDTO todoDTO);

    ListResponseDto<TodoDTO> list(ListRequestDTO listRequestDTO);
}
