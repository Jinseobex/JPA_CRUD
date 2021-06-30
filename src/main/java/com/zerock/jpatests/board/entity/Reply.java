package com.zerock.jpatests.board.entity;

import com.zerock.jpatests.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_reply")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    //디폴트로 LAZY로 쓸 것!
    //board가 필요할 때까지 로딩 안 할거야
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
