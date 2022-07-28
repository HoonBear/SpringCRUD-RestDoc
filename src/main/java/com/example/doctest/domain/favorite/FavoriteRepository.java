package com.example.doctest.domain.favorite;

import com.example.doctest.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
