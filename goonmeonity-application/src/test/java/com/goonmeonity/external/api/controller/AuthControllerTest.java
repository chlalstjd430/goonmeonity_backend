package com.goonmeonity.external.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.external.api.request.SignInRequest;
import com.goonmeonity.external.api.request.SignUpRequest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AuthControllerTest extends CommonTest{

    @Test
    public void 회원가입_성공() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest(
                "unit3@test.com",
                "1234567890123456789012345678901234567890123456789012345678901234",
                "unit_test3"
        );
        String url = "http://localhost:" + port + "/v1/auth/sign-up";

        //when
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(signUpRequest))
        ).andReturn();

        //then
        System.out.println(url);
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    public void 로그인_성공() throws Exception {
        //given
        String token = signIn();
        System.out.println(token);
        String url = "http://localhost:" + port + "/v1/auth/sign-in";
        SignInRequest signInRequest = new SignInRequest(
                "unit@test.com",
                "1234567890123456789012345678901234567890123456789012345678901234"
        );

        //when
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(signInRequest))
        ).andReturn();

        //then
        System.out.println(url);
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
