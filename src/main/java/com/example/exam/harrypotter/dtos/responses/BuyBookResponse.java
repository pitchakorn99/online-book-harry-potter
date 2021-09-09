package com.example.exam.harrypotter.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuyBookResponse {
    private List<BuyBookResponseDetail> detail;
    private double price;
    private double discount;
    private double totalPrice;
}
