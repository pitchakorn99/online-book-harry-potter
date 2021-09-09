package com.example.exam.harrypotter.controller;

import com.example.exam.harrypotter.dtos.queries.BuyBookQuery;
import com.example.exam.harrypotter.dtos.responses.BuyBookResponse;
import com.example.exam.harrypotter.services.BuyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyBookController {
    @Autowired
    private BuyBookService buyBookService;

    @PostMapping(value = "/buy-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BuyBookResponse buyBook(@RequestBody BuyBookQuery query){
        BuyBookResponse response = new BuyBookResponse();

        response = this.buyBookService.byBook(query);

        return response;
    }

    @PostMapping(value = "/buy-book/submit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BuyBookResponse buyBookSubmit(@RequestBody BuyBookQuery query){
        BuyBookResponse response = new BuyBookResponse();

        response = this.buyBookService.byBook(query);

        return response;
    }
}
