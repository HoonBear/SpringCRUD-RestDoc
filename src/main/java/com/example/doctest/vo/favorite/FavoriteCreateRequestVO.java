package com.example.doctest.vo.favorite;

import lombok.Data;

@Data
public class FavoriteCreateRequestVO {
    private String favoriteName;
    private Long memberId;
}
