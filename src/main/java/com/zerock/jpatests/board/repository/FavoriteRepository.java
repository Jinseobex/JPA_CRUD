package com.zerock.jpatests.board.repository;

import com.zerock.jpatests.board.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
