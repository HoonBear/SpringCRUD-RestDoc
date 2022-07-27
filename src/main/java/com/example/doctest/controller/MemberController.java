package com.example.doctest.controller;

import com.example.doctest.dto.member.MemberCreateDTO;
import com.example.doctest.dto.member.MemberDeleteDTO;
import com.example.doctest.dto.member.MemberUpdateDTO;
import com.example.doctest.service.member.MemberService;
import com.example.doctest.vo.member.MemberCreateRequestVO;
import com.example.doctest.vo.member.MemberCreateResponseVO;
import com.example.doctest.vo.member.MemberDeleteRequestVO;
import com.example.doctest.vo.member.MemberReadResponseVO;
import com.example.doctest.vo.member.MemberUpdateRequestVO;
import java.util.Optional;
import javax.security.auth.login.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final ModelMapper modelMapper;

    @PostMapping("/member")
    public ResponseEntity createMember(@RequestBody MemberCreateRequestVO memberCreateRequestVO){
        MemberCreateDTO request = modelMapper.map(memberCreateRequestVO, MemberCreateDTO.class);
        MemberCreateResponseVO response = memberService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(response);
    }

    @GetMapping("/member/{email}")
    public ResponseEntity readMemberByEmail(@PathVariable String email){
        System.out.println(email);
        Optional<MemberReadResponseVO> response = memberService.readUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(response);
    }

    @PutMapping("/member")
    public ResponseEntity updateUser(@RequestBody MemberUpdateRequestVO memberUpdateRequestVO)
        throws AccountNotFoundException {
        MemberUpdateDTO request = modelMapper.map(memberUpdateRequestVO, MemberUpdateDTO.class);
        MemberReadResponseVO response = memberService.updateUser(request);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(response);
    }

    @DeleteMapping("/member")
    public ResponseEntity deleteUser(@RequestBody MemberDeleteRequestVO memberDeleteRequestVO)
        throws AccountNotFoundException {
        MemberDeleteDTO request = modelMapper.map(memberDeleteRequestVO, MemberDeleteDTO.class);
        MemberReadResponseVO response = memberService.deleteUser(request);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(response);
    }
}
