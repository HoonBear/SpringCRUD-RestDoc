package com.example.doctest.service.favorite;


import com.example.doctest.domain.favorite.Favorite;
import com.example.doctest.domain.favorite.FavoriteRepository;
import com.example.doctest.domain.member.MemberRepository;
import com.example.doctest.dto.favorite.FavoriteCreateDTO;
import com.example.doctest.vo.favorite.FavoriteCreateResponseVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ModelMapper modelMapper;

    @Override
    public FavoriteCreateResponseVO createFavorite(FavoriteCreateDTO favoriteCreateDto) {
        Favorite favorite = modelMapper.map(favoriteCreateDto, Favorite.class);
        favoriteRepository.save(favorite);
        FavoriteCreateResponseVO response = modelMapper.map(favorite, FavoriteCreateResponseVO.class);
        return response;
    }
}
