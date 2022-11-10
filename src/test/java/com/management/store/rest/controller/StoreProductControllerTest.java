package com.management.store.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.store.app.ManagementAppApplication;
import com.management.store.model.entity.StoreProduct;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = StoreProductController.class)
@ContextConfiguration(classes = ManagementAppApplication.class)
@AutoConfigureMockMvc
public class StoreProductControllerTest {
    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void productsEmptyList() {
        mock.perform(get("/api/product/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(List.of().toString()));
    }

    @SneakyThrows
    @Test
    @DirtiesContext
    void productsNonEmptyList() {
        int itemsToCreate = (int) (Math.random() * 10);
        Stream.iterate(0, i -> i + 1)
                .limit(itemsToCreate)
                .forEach(i -> callAddEndpoint());

        mock.perform(get("/api/product/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(itemsToCreate)));
    }

    @SneakyThrows
    private void callAddEndpoint() {
        mock.perform(post("/api/product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new StoreProduct(-1L, "UT Product", 100.01, "USD")))
        ).andExpect(status().isOk());
    }
}