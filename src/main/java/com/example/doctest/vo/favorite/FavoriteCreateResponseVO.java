package com.example.doctest.vo.favorite;

import lombok.Data;

@Data
public class FavoriteCreateResponseVO {
    private Long Id;
    private String favoriteName;
    private Long memberId;
}
