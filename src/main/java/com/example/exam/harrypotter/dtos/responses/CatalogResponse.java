package com.example.exam.harrypotter.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogResponse {
    private int id;
    private String code;
    private String name;
    private double price;
    private int balance;
}
