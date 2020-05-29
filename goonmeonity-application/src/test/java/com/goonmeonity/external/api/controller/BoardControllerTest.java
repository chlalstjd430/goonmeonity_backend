package com.goonmeonity.external.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.external.api.request.CreateBoardRequest;
import com.goonmeonity.external.api.request.SignInRequest;
import com.goonmeonity.external.api.request.SignUpRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class BoardControllerTest extends CommonTest{
    @Test
    public void 게시글_작성_성공() throws Exception{
        String token = signIn();
        //given
        String url = "http://localhost:" + port + "/v1/boards/board";
        CreateBoardRequest createBoardRequest = new CreateBoardRequest(
                "테스트_제목",
                "테스트 내용",
                BoardCategory.COUNSEL
        );

        //when
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("accessToken", token)
                        .content(new ObjectMapper().writeValueAsString(createBoardRequest))
        ).andReturn();

        //then
        System.out.println(url);
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(201, mvcResult.getResponse().getStatus());
    }
}
