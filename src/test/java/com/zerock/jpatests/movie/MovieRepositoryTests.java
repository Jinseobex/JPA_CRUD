package com.zerock.jpatests.movie;

import com.zerock.jpatests.movie.entity.Movie;
import com.zerock.jpatests.movie.repository.MovieRepository;
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

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void test1() {
        log.info(movieRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 300).forEach(i->{
            Movie movie = Movie.builder()
                    .title("제목"+i)
                    .actor("배우"+i)
                    .director("감독"+i)
                    .releaseDate(201901L+i)
                    .movieDesc("영화소개"+i)
                    .build();
            movieRepository.save(movie);

        });
    }

    @Test
    public void testSelect() {
        Optional<Movie> result = movieRepository.findById(2L);
        result.ifPresent(Movie -> {
            log.info(Movie);
        });
    }

    @Test
    public void testUpdate() {
        Optional<Movie> result = movieRepository.findById(2L);
        result.ifPresent(Movie -> {
            Movie.changeTitle("블랙위도우");
            movieRepository.save(Movie);
        });
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("no").ascending());
        Page<Movie> result = movieRepository.findAll(pageable);
        result.getContent().forEach(Movie -> {
            log.info(Movie);
        });
    }

    @Test
    public void testDelete() {
        movieRepository.delete(Movie.builder().no(2L).build());
    }





}
