package com.example.exam.harrypotter.controller;

import com.example.exam.harrypotter.dtos.queries.BuyBookDetail;
import com.example.exam.harrypotter.dtos.queries.BuyBookQuery;
import com.example.exam.harrypotter.dtos.responses.BuyBookResponse;
import com.example.exam.harrypotter.dtos.responses.CatalogResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuyBookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private BuyBookQuery buyBookQuery;

    @BeforeEach
    public void initTestData(){
        this.buyBookQuery = new BuyBookQuery();
        List<BuyBookDetail> buyBookDetails = new ArrayList<>();
        // Add Buy Book
        BuyBookDetail bbd01 = new BuyBookDetail();
        bbd01.setBookCode("B0001");
        bbd01.setAmount(2);
        buyBookDetails.add(bbd01);
        BuyBookDetail bbd02 = new BuyBookDetail();
        bbd02.setBookCode("B0002");
        bbd02.setAmount(2);
        buyBookDetails.add(bbd02);
        BuyBookDetail bbd03 = new BuyBookDetail();
        bbd03.setBookCode("B0003");
        bbd03.setAmount(1);
        buyBookDetails.add(bbd03);
        // End Add Book
        buyBookQuery.setBuy(buyBookDetails);
    }

    @Test
    @DisplayName("Get calculate price and discount")
    public void case01() {
        BuyBookResponse response = restTemplate.postForObject("/buy-book", this.buyBookQuery, BuyBookResponse.class);
        assertEquals(3, response.getDetail().size());
        assertEquals(500, response.getPrice());
        assertEquals(50, response.getDiscount());
        assertEquals(450, response.getTotalPrice());
    }

    @Test
    @DisplayName("Submit buy book")
    public void case02() {
        boolean response = restTemplate.postForObject("/buy-book/submit", this.buyBookQuery, boolean.class);
        assertTrue(response);
    }

    @Test
    @DisplayName("Check catalog balance")
    public void case03(){
        CatalogResponse[] catalogs = restTemplate.getForObject("/catalog", CatalogResponse[].class);
        assertEquals(98, Arrays.stream(catalogs).filter(f -> f.getCode().equals("B0001")).findFirst().get().getBalance());
        assertEquals(98, Arrays.stream(catalogs).filter(f -> f.getCode().equals("B0002")).findFirst().get().getBalance());
        assertEquals(99, Arrays.stream(catalogs).filter(f -> f.getCode().equals("B0003")).findFirst().get().getBalance());
    }
}