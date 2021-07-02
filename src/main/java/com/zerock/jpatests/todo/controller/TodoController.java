package com.zerock.jpatests.todo.controller;

import com.zerock.jpatests.common.dto.ListResponseDto;
import com.zerock.jpatests.todo.dto.ListRequestDTO;
import com.zerock.jpatests.todo.dto.TodoDTO;
import com.zerock.jpatests.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDto<TodoDTO>> list(ListRequestDTO listRequestDTO){

        log.info(listRequestDTO);

        return ResponseEntity.ok(todoService.list(listRequestDTO));
    }


    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody TodoDTO todoDTO){

        log.info("register................." + todoDTO);

        Long tno = todoService.register(todoDTO);

        log.info("RESULT: " + tno);

        return ResponseEntity.ok().body(tno);
    }

    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long tno ){

        TodoDTO dto = todoService.read(tno);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable Long tno){

        Long deletedTno = todoService.remove(tno);

        return ResponseEntity.ok().body(deletedTno);
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno, @RequestBody TodoDTO todoDTO){

        todoDTO.setTno(tno);

        log.info(todoDTO);

        TodoDTO resultDTO = todoService.modify(todoDTO);

        return ResponseEntity.ok(resultDTO);
    }

}
