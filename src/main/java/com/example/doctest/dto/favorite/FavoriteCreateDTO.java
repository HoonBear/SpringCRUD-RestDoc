package com.example.doctest.dto.favorite;

import lombok.Data;

@Data
public class FavoriteCreateDTO {
    private String favoriteName;
    private Long memberId;
}
