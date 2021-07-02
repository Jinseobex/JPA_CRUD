package com.zerock.jpatests.board.entity;

import com.zerock.jpatests.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_favorite", indexes = @Index(name = "idx_board", columnList = "board_bno"))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private boolean mark;

    private String actor;

    @ManyToOne
    private Board board;


}
