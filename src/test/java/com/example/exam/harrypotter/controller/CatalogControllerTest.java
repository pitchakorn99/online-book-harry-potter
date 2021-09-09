package com.example.exam.harrypotter.controller;

import com.example.exam.harrypotter.dtos.responses.CatalogResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Get all product in catalog")
    public void case01(){
        CatalogResponse[] catalogs = restTemplate.getForObject("/catalog", CatalogResponse[].class);
        assertEquals(5, catalogs.length);
    }

    @Test
    @DisplayName("Get first book in catalog (id=1)")
    public void case02(){
        CatalogResponse catalogs = restTemplate.getForObject("/catalog/1", CatalogResponse.class);
        assertEquals(1, catalogs.getId());
        assertEquals("first book", catalogs.getName());
        assertEquals(100, catalogs.getBalance());
    }

}