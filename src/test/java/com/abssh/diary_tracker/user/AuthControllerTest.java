package com.abssh.diary_tracker.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.abssh.diary_tracker.IntegrationTest;

@AutoConfigureMockMvc
public class AuthControllerTest extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    /* Register endpoint */
    @Test
    void shouldRegisterAndReturn201() throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "plainPassword123"
                    }    
                        """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.accessToken").isNotEmpty())
            .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void shouldReturn400WhenRegisterBodyIsInvalid() throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "",
                        "plainPassword": ""
                    }    
                        """))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn409WhenUsernameAlreadyExists() throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "plainPassword123"
                    }
                """));

        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "anotherPlainPassword123"
                    }    
                        """))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    /* Login endpoint */
    @Test
    void shouldLoginAndReturn200() throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "plainPassword123"
                    }
                """));

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "plainPassword123"
                    }    
                        """))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accessToken").isNotEmpty())
            .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void shouldReturn400WhenLoginBodyIsInvalid() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "",
                        "plainPassword": ""
                    }    
                        """))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn401WhenCredentialsAreWrong() throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "plainPassword123"
                    }
                """));

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "plainPassword": "wrongPassword123"
                    }    
                        """))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.message").isNotEmpty());
    }
}
