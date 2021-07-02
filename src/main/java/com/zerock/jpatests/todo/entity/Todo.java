package com.zerock.jpatests.todo.entity;

import com.zerock.jpatests.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_todo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity {

    //모든 entity는 PK를 가진다. 없으면 에러가 난다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이 타입을 쓰면 DB마다 알아서 생성해줌
    private Long tno;

    @Column(nullable = false, length = 300)
    private String content;

    private boolean del;

    //원래 setter를 만들지 않기 때문에 setter
    public void changeTitle(String content) {
        this.content = content;
    }

    public void changeDel(boolean del) {
        this.del = del;
    }

}