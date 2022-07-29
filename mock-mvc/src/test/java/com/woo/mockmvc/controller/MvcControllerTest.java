package com.woo.mockmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woo.mockmvc.HelloData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebMvcTest
class MvcControllerTest {

    MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MvcController())
                .build();
    }

//    @BeforeEach
//    void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }


    @Test
    @DisplayName("ModelAttribute 컨트롤러")
    void param() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/param")
                        .param("name", "Lee")
                        .param("age", "25"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello! Lee"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("RequestBody 컨트롤러")
    void content() throws Exception {

        String content = objectMapper.writeValueAsString(new HelloData("Lee", "20"));

        mockMvc.perform(MockMvcRequestBuilders.post("/content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello! Lee"))
                .andDo(MockMvcResultHandlers.print());
    }



}