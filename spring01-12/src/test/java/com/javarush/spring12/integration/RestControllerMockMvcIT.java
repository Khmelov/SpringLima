package com.javarush.spring12.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestHtmlAndJsonContext
@AutoConfigureMockMvc
@RequiredArgsConstructor
class RestControllerMockMvcIT {

    private final MockMvc mockMvc;

    @Test
    void postUser() throws Exception {
        this.mockMvc.perform(
                        post("/api/rest/v1/users")
                                .accept(MediaType.ALL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                          {
                                          "login": "qweqwe",
                                          "password": "qweqwe"
                                          }
                                        """)
                )
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful()
                );
    }

    @Test
    void deleteUser() throws Exception {
        this.mockMvc.perform(
                        delete("/api/rest/v1/users/1")
                                .accept(MediaType.ALL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                          {
                                          "id": 1,
                                          "login": "qweqwe",
                                          "password": "qweqwe"
                                          }
                                        """)
                )
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful()
                );
    }

    @Test
    void putUser() throws Exception {
        this.mockMvc.perform(
                        put("/api/rest/v1/users/1")
                                .accept(MediaType.ALL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                          {
                                          "id": 1,
                                          "login": "qweqwe",
                                          "password": "qweqwe"
                                          }
                                        """)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("login").value("qweqwe"))
                .andExpect(jsonPath("password").value("qweqwe"))
        ;
    }
}