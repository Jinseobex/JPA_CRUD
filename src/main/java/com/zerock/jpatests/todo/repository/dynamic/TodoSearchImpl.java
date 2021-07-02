package com.zerock.jpatests.todo.repository.dynamic;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.zerock.jpatests.todo.entity.QTodo;
import com.zerock.jpatests.todo.entity.Todo;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Todo doA() {

        log.warn("doA.....");
        log.warn("doA.....");
        log.warn("doA.....");

        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);

        //where content like '%9%' and tno > 170
        //where todo.tno = 170
        query.where(todo.tno.gt(170));
        //content like '%9%' === expression이라고 부릅니다. like를 사용 할 때는 % 확인할 것
        //BooleanExpression con1 = todo.content.like("%9%");

        query.orderBy(todo.tno.desc());

        query.offset(0);
        query.limit(10);
        //count값은 long값으로!

        List<Todo> result = query.fetch();
        long count = query.fetchCount();

        log.warn("===========================");
        log.info("COUNT: "+count);
        log.warn(result);

        return null;
    }

    @Override
    public Page<Todo> listWithSearch(String keyword, Pageable pageable) {
        log.warn("listWithSearch...................");
        
        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);
        
        //trim이 의외로 리소스를 잡아먹는다.
        if(keyword != null && keyword.trim().length() != 0) {
            query.where(todo.content.contains(keyword));
        }
        //인덱스를 타도록 유도하는 문
        query.where(todo.tno.gt(0L));
        query.orderBy(todo.tno.desc());

        //Pageable = 0, 10
        //paging
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

}
