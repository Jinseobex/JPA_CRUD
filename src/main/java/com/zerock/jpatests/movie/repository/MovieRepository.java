package com.zerock.jpatests.movie.repository;
import com.zerock.jpatests.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
