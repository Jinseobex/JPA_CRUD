package com.zerock.jpatests.movie.entity;

import com.zerock.jpatests.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_movie")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private Long releaseDate;

    @Column(nullable = false)
    private String movieDesc;

    private boolean del;

    public void changeTitle(String title) {
        this.title = title;
    }

}
