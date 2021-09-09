package com.example.exam.harrypotter.dtos.queries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyBookDetail {
    private String bookCode;
    private int amount;
}
