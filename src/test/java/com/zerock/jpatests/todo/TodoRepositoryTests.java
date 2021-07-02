package com.zerock.jpatests.todo;

import com.zerock.jpatests.todo.entity.Todo;
import com.zerock.jpatests.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Todo todo = Todo.builder().content("내용...."+i).build();
            todoRepository.save(todo);
        }); //loop
    }

    @Test
    public void testSelect() {

        //Optional 이라는 타입을 사용한다는 것을 기억하자.
        Optional<Todo> result = todoRepository.findById(1L);

        //log.info(result.get());

        //if else를 줄여쓰는 방법이다.
        result.ifPresent(todo -> log.info(todo));

    }

    @Test
    public void testPaging() {

        //페이지 처리는 Pageable이라는 타입을 이용한다.
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        //페이징 처리의 리턴 타입은 Pageable이다.
        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(todo -> log.info(todo));

    }

    @Test
    public void testUpdate() {
        //update에서 select를 두 번 하는 이유는 영속성 때문이다.
        //먼저 한번 선택을 하고, 영속성 컨텍스트에서 변경한 후에 다시 한번 select를 하여 감지하는 것.
        //엔티티에서 db와 값이 다르구나 하고 db의 값을 바꿔주는 것과 비슷하게 생각하면 된다.
        Optional<Todo> result = todoRepository.findById(300L);

        result.ifPresent(todo -> {
            todo.changeTitle("300번 내용 수정");
            todoRepository.save(todo);
        });

    }

    @Test
    public void testDelete() {

        // 왜 select가 계속 먼저 나오는가?
        todoRepository.delete(Todo.builder().tno(300L).build());

    }

    @Test
    public void testList1() {
        //count는 무조건 long 타입이다!
        String keyword="15";
        Pageable pageable = PageRequest.of(0, 10);

        Page<Todo> result = todoRepository.getList(keyword, pageable);

        log.info(result.getTotalElements());

        result.getContent().forEach(todo -> {
            log.info(todo);
        });
    }

    @Test
    public void testDoA() {
        todoRepository.doA();
    }

    @Test
    public void testListWithSearch() {

        String keyword = "10";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Todo> result = todoRepository.listWithSearch(keyword, pageable);

        log.info(result.getTotalElements());
        result.getContent().forEach(todo -> {
            log.info(todo);
        });

    }

}
