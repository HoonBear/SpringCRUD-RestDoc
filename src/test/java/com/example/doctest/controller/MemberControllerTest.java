package com.example.doctest.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.doctest.DatabaseCleanup;
import com.example.doctest.domain.member.MemberRepository;
import com.example.doctest.dto.member.MemberCreateDTO;
import com.example.doctest.service.member.MemberService;
import com.example.doctest.vo.member.MemberCreateRequestVO;
import com.example.doctest.vo.member.MemberDeleteRequestVO;
import com.example.doctest.vo.member.MemberUpdateRequestVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class MemberControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                      .addFilters(new CharacterEncodingFilter("UTF-8", true))
                                      .apply(documentationConfiguration(restDocumentation))
                                      .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()),
                                          preprocessResponse(prettyPrint())))
                                      .build();

        databaseCleanup.execute();

        MemberCreateDTO req = new MemberCreateDTO();
        req.setEmail("defaultTest@naver.com");
        req.setPassword("1234");
        memberService.createUser(req);
    }

    @Test
    public void createMember() throws Exception {
        MemberCreateRequestVO req = new MemberCreateRequestVO();
        req.setEmail("createByTest@naver.com");
        req.setPassword("1234");

        String content = objectMapper.writeValueAsString(req);

        this.mockMvc.perform(post("/api/member").content(content).accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(document("createMember", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                            PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("email")
                                                .description("회원가입 이메일")
                                                .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("deleteYn")
                                                .description("DEFAULT")
                                                .type(JsonFieldType.STRING)
                            )
                        )
                    )
                    .andDo(print());
    }

    @Test
    public void readMemberByEmail() throws Exception {
        this.mockMvc.perform(get("/api/member/{email}","defaultTest@naver.com").accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andDo(document("readMemberByEmail", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), PayloadDocumentation.responseFields(
                            PayloadDocumentation.fieldWithPath("email")
                                                .description("회원가입 이메일")
                                                .type(JsonFieldType.STRING).optional(),
                            PayloadDocumentation.fieldWithPath("deleteYn")
                                                .description("DEFAULT")
                                                .type(JsonFieldType.STRING).optional(),
                            PayloadDocumentation.fieldWithPath("id")
                                                 .description("Primary Key")
                                                 .type(JsonFieldType.NUMBER).optional()
                            )
                        )
                    )
                    .andDo(print());
    }

    @Test
    public void updateMember() throws Exception {
        MemberUpdateRequestVO req = new MemberUpdateRequestVO();
        req.setId(1L);
        req.setPassword("12345");
        String content = objectMapper.writeValueAsString(req);
        this.mockMvc.perform(put("/api/member").content(content).accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andDo(document("updateMember", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        PayloadDocumentation.responseFields(
                            PayloadDocumentation.fieldWithPath("email")
                                                .description("회원가입 이메일")
                                                .type(JsonFieldType.STRING),
                            PayloadDocumentation.fieldWithPath("deleteYn")
                                                .description("DEFAULT")
                                                .type(JsonFieldType.STRING),
                            PayloadDocumentation.fieldWithPath("id")
                                                 .description("Primary Key")
                                                 .type(JsonFieldType.NUMBER)
                            )
                        )
                    )
                    .andDo(print());
    }

    @Test
    public void deleteMember() throws Exception {
        MemberDeleteRequestVO req = new MemberDeleteRequestVO();
        req.setId(1L);
        String content = objectMapper.writeValueAsString(req);
        this.mockMvc.perform(delete("/api/member").content(content).accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andDo(document("deleteMember", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        PayloadDocumentation.responseFields(
                            PayloadDocumentation.fieldWithPath("email")
                                                .description("회원가입 이메일")
                                                .type(JsonFieldType.STRING),
                            PayloadDocumentation.fieldWithPath("deleteYn")
                                                .description("DEFAULT")
                                                .type(JsonFieldType.STRING),
                            PayloadDocumentation.fieldWithPath("id")
                                                 .description("Primary Key")
                                                 .type(JsonFieldType.NUMBER)
                            )
                        )
                    )
                    .andDo(print());
    }
}