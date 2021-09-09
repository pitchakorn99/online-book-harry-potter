package com.example.exam.harrypotter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tbl_catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_code")
    private String bookCode;
    @Column(name = "book_name")
    private String bookName;
    private String unit;
    @Column(name = "price_per_unit")
    private double pricePerUnit;
    private int balance;
}
