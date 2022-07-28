package com.example.doctest.domain.member;

import com.example.doctest.domain.favorite.Favorite;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

}
