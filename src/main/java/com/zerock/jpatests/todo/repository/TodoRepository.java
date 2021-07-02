package com.zerock.jpatests.todo.repository;

import com.zerock.jpatests.todo.entity.Todo;
import com.zerock.jpatests.todo.repository.dynamic.TodoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

    @Query("select t from Todo t where t.content like concat('%', :keyword, '%') order by t.tno desc")
    Page<Todo> getList(String keyword, Pageable pageable);
    // 여기에 limit는 쓰지 말 것

    @Modifying
    @Query("update Todo set content = :content where tno = :tno")
    void updateContent(String content, Long tno);

}
