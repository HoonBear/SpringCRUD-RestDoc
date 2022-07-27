package com.example.doctest.service.member;

import com.example.doctest.domain.member.Member;
import com.example.doctest.domain.member.MemberRepository;
import com.example.doctest.dto.member.MemberCreateDTO;
import com.example.doctest.dto.member.MemberDeleteDTO;
import com.example.doctest.dto.member.MemberUpdateDTO;
import com.example.doctest.vo.member.MemberCreateResponseVO;
import com.example.doctest.vo.member.MemberReadResponseVO;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;
import javax.security.auth.login.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public MemberCreateResponseVO createUser(MemberCreateDTO memberCreateDto) {
        Member member = modelMapper.map(memberCreateDto, Member.class);
        memberRepository.save(member);
        MemberCreateResponseVO response = modelMapper.map(member, MemberCreateResponseVO.class);
        return response;
    }

    @Override
    public Optional<MemberReadResponseVO> readUserByEmail(String email) {
        MemberReadResponseVO response = modelMapper.map(memberRepository.findByEmail(email),
            MemberReadResponseVO.class);

        return Optional.ofNullable(response);
    }

    @Override
    public MemberReadResponseVO updateUser(MemberUpdateDTO memberUpdateDto) throws AccountNotFoundException {

        Member member = memberRepository.findById(memberUpdateDto.getId())
                                        .orElseThrow(
                                            AccountNotFoundException::new);
        member.setPassword(memberUpdateDto.getPassword());
        memberRepository.save(member);
        MemberReadResponseVO response = modelMapper.map(member, MemberReadResponseVO.class);
        return response;
    }

    @Override
    public MemberReadResponseVO deleteUser(MemberDeleteDTO memberDeleteDto) throws AccountNotFoundException {
        Member member = memberRepository.findById(memberDeleteDto.getId())
                                        .orElseThrow(
                                            AccountNotFoundException::new);
        member.setDeleteYn("Y");
        memberRepository.save(member);
        MemberReadResponseVO response = modelMapper.map(member, MemberReadResponseVO.class);
        return response;
    }
}
