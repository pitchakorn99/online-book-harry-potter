package com.example.exam.harrypotter.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyBookResponseDetail {
    private String bookCode;
    private String bookName;
    private int amount;
    private double pricePerUnit;
    private double totalPrice;
}
