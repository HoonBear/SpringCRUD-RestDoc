package com.example.doctest.vo.member;

import lombok.Data;

@Data
public class MemberCreateRequestVO {

    private String email;
    private String password;
    private String deleteYn;
}
