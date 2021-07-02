package com.zerock.jpatests.todo.service;

import com.zerock.jpatests.common.dto.ListResponseDto;
import com.zerock.jpatests.common.dto.PageMaker;
import com.zerock.jpatests.common.dto.ListRequestDTO;
import com.zerock.jpatests.todo.dto.TodoDTO;
import com.zerock.jpatests.todo.entity.Todo;
import com.zerock.jpatests.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public ListResponseDto<TodoDTO> list(ListRequestDTO dto) {

        Pageable pageable = dto.getPageable();

        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
                .map((todo) -> EntityToDto(todo))
                .collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(),(int)result.getTotalElements());

        log.info(pageMaker);

        return  ListResponseDto.<TodoDTO>builder()
                .dtoList(dtoList)
                .pageMaker(pageMaker)
                .listRequestDTO(dto)
                .build();

    }



    @Override
    public Long register(TodoDTO dto) {

        log.info(dto);

        Todo entity = dtoToEntity(dto);

        Todo result = todoRepository.save(entity);

        return result.getTno();
    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        log.info(result);

        if(result.isPresent()){
            Todo todo = result.get();
            return EntityToDto(todo);
        }

        return null;
    }

    @Override
    public Long remove(Long tno) {

        todoRepository.deleteById(tno);

        return tno;
    }

    @Override
    public TodoDTO modify(TodoDTO todoDTO) {

        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        if(result.isPresent()){

            Todo entity = result.get();
            entity.changeTitle(todoDTO.getContent());
            entity.changeDel(todoDTO.isDel());

            Todo saveResult = todoRepository.save(entity);

            return EntityToDto(saveResult);
        }
        return null;

    }

}
