package com.zerock.jpatests.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // getter와 setter에서 자유롭다, 마음대로 변경이 가능하다는 것을 의미함.
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    //비닐봉지와 같은 일을 하는 DTO 클래스
    //Entity와 가장 다른 점은 getter/setter에서 자유롭다.
    private Long tno;
    private String content;
    private boolean del;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
