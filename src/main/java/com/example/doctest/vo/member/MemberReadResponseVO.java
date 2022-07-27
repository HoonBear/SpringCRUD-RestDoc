package com.example.doctest.vo.member;

import lombok.Data;

@Data
public class MemberReadResponseVO {
    private Long Id;
    private String email;
    private String deleteYn;
}
