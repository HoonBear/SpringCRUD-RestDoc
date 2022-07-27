package com.example.doctest.dto.member;

import lombok.Data;

@Data
public class MemberCreateDTO {
    private String email;
    private String password;
    private final String deleteYn = "N";
}
