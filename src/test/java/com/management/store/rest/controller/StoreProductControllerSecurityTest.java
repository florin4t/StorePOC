package com.management.store.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.store.app.ManagementAppApplication;
import com.management.store.model.entity.StoreProduct;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {ManagementAppApplication.class})
@ActiveProfiles("secure")
public class StoreProductControllerSecurityTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @SneakyThrows
    @Test
    void getProducts_whenNotAuthenticated_fails() {
        mockMvc.perform(get("/api/product/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @SneakyThrows
    @Test
    void getProducts_withBasicUser_isSuccess() {
        mockMvc.perform(get("/api/product/all")
                        .with(httpBasic("basic", "basic"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void getProducts_withInvalidUser_fails() {
        mockMvc.perform(get("/api/product/all")
                        .with(user("invalid").password("invalid"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void addProduct_withBasicUser_fails() {
        mockMvc.perform(post("/api/product/add")
                        .with(httpBasic("basic", "basic"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new StoreProduct(-1L, "UT Product", 100.01, "USD")))
                )
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void addProduct_withAdminUser_success() {
        mockMvc.perform(post("/api/product/add")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new StoreProduct(-1L, "UT Product", 100.01, "USD")))
                )
                .andExpect(status().isOk());
    }
}
