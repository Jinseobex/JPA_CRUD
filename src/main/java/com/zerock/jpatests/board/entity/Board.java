package com.zerock.jpatests.board.entity;

import com.zerock.jpatests.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private String writer;
}
