package com.example.doctest.service.member;

import com.example.doctest.dto.member.MemberCreateDTO;
import com.example.doctest.dto.member.MemberDeleteDTO;
import com.example.doctest.dto.member.MemberUpdateDTO;
import com.example.doctest.vo.member.MemberCreateResponseVO;
import com.example.doctest.vo.member.MemberReadResponseVO;
import java.util.Optional;
import javax.security.auth.login.AccountNotFoundException;

public interface MemberService {

    MemberCreateResponseVO createUser(MemberCreateDTO memberCreateDto);

    Optional<MemberReadResponseVO> readUserByEmail(String email);

    MemberReadResponseVO updateUser(MemberUpdateDTO memberUpdateDto) throws AccountNotFoundException;

    MemberReadResponseVO deleteUser(MemberDeleteDTO memberDeleteDto) throws AccountNotFoundException;
}
