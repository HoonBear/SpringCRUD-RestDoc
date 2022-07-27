package com.example.doctest.dto.member;

import lombok.Data;

@Data
public class MemberUpdateDTO {
    private Long Id;
    private String password;
    private final String deleteYn = "Y";
}
