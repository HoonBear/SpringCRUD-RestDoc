package com.example.doctest.service.favorite;

import static org.junit.jupiter.api.Assertions.*;

import com.example.doctest.domain.favorite.Favorite;
import com.example.doctest.domain.favorite.FavoriteRepository;
import com.example.doctest.domain.member.Member;
import com.example.doctest.domain.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FavoriteServiceImplTest {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void readFavorite() {
        List<Favorite> favorite = favoriteRepository.findByMemberId(1L);
        //Member member = favorite.get(0).getMember();

        System.out.println(favorite.toString());
 //        Member member = new Member();
//        member.setFavoriteList(favorite);
//
//
//        System.out.println(member);
    }

    @Test
    void memberFirst() {
    Member member = memberRepository.findById(1L).orElseThrow();



        List<Favorite> favorite = member.getFavoriteList();


        //member.addToFavorite();


    }
}