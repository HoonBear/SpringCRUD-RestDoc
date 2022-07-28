package com.example.doctest.service.favorite;

import com.example.doctest.dto.favorite.FavoriteCreateDTO;
import com.example.doctest.vo.favorite.FavoriteCreateResponseVO;

public interface FavoriteService {
    FavoriteCreateResponseVO createFavorite(FavoriteCreateDTO favoriteCreateDto);
}
