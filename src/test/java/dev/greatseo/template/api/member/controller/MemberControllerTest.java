package dev.greatseo.template.api.member.controller;

import dev.greatseo.template.api.member.dto.MemberResponseDto;
import dev.greatseo.template.api.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService service;

    @Test
    public void member_컨트롤러레이어_test()  throws Exception {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(1L);
        dto.setEmail("sjseo85@gmail.com");
        dto.setName("sungjin.seo");
        dto.setMobile("01066850501");
        dto.setNickname("greatseo");
        dto.setPassword("sjseo85");
        dto.setProfile("JIN");
        dto.setIsDeleted("N");

        // given
        given(service.getMemberById(1L)).willReturn(dto);

        // when으로 호출시는 실제로 호출하는게 맞는건가??
        //when(service.getMemberById(1L)).thenReturn(new MemberResponseDto()).toString();

        //this.mockMvc.perform(get("/api/v1/member/1")).andDo(print()).andExpect(status().isOk())
        //        .andExpect(content().json(new MemberResponseDto().toString()));

        mockMvc.perform(get("/api/v1/member/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("sungjin.seo")));

    }

//    @Test
//    public void testGetList() throws Exception {
//        List<StageResponseDto> list = Arrays.asList(
//                new StageResponseDto("id2", "sName2", "sCode2", "this is s2"));
//
//        given(service.findAll()).willReturn(list);
//
//        mockMvc.perform(get("/api").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is("sName2")));
//    }

}