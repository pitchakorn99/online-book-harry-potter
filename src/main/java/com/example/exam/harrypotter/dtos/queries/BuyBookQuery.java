package com.example.exam.harrypotter.dtos.queries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuyBookQuery {
    @JsonProperty("buy")
    private List<BuyBookDetail> buy;
}