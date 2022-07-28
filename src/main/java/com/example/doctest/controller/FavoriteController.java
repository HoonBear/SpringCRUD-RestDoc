package com.example.doctest.controller;

import com.example.doctest.dto.favorite.FavoriteCreateDTO;
import com.example.doctest.service.favorite.FavoriteService;
import com.example.doctest.vo.favorite.FavoriteCreateRequestVO;
import com.example.doctest.vo.favorite.FavoriteCreateResponseVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final ModelMapper modelMapper;

    @PostMapping("/favorite")
    public ResponseEntity createFavorite(@RequestBody FavoriteCreateRequestVO favoriteCreateRequestVO) {
        FavoriteCreateDTO request = modelMapper.map(favoriteCreateRequestVO, FavoriteCreateDTO.class);
        FavoriteCreateResponseVO response = favoriteService.createFavorite(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(response);
    }
}
