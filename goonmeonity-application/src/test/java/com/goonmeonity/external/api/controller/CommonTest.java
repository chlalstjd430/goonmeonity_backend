package com.goonmeonity.external.api.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.external.api.request.SignUpRequest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class CommonTest {
    @LocalServerPort
    protected int port;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    protected String signUp(int num) throws Exception{
        SignUpRequest signUpRequest = new SignUpRequest(
                "unit" + num + "@test.com",
                "1234567890123456789012345678901234567890123456789012345678901234",
                "unit_test" + num
        );
        String url = "http://localhost:" + port + "/v1/auth/sign-up";

        //when
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(signUpRequest))
        ).andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        String token = new JSONObject(obj.get("accessToken").toString()).get("token").toString();
        return token;
    }

}
