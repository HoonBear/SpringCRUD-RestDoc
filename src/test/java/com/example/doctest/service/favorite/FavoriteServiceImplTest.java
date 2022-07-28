package com.example.doctest.service.favorite;

import static org.junit.jupiter.api.Assertions.*;

import com.example.doctest.domain.favorite.Favorite;
import com.example.doctest.domain.favorite.FavoriteRepository;
import com.example.doctest.domain.member.Member;
import com.example.doctest.domain.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class FavoriteServiceImplTest {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @Transactional
    void readFavorite() {
        List<Favorite> favorite = favoriteRepository.findByMemberId(1L);
        System.out.println(favorite);
    }

    @Test
    @Transactional
    void memberFirst() {
        Member member = memberRepository.findById(1L).orElseThrow();
        System.out.println(member);
    }
}