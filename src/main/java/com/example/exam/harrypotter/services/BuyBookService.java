package com.example.exam.harrypotter.services;

import com.example.exam.harrypotter.dtos.queries.BuyBookDetail;
import com.example.exam.harrypotter.dtos.queries.BuyBookQuery;
import com.example.exam.harrypotter.dtos.responses.BuyBookResponse;
import com.example.exam.harrypotter.dtos.responses.BuyBookResponseDetail;
import com.example.exam.harrypotter.entity.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BuyBookService {

    private CatalogService catalogService;

    @Autowired
    public BuyBookService(
            CatalogService catalogService
    ){
        this.catalogService = catalogService;
    }

    public BuyBookResponse byBook(BuyBookQuery query){
        BuyBookResponse response = new BuyBookResponse();

        List<Catalog> bookList = getBookData(query.getBuy());

        response.setDetail(bookList.stream().map(x -> {
            BuyBookResponseDetail de = new BuyBookResponseDetail();
            de.setBookCode(x.getBookCode());
            de.setBookName(x.getBookName());
            Optional<BuyBookDetail> amount = query.getBuy()
                    .stream()
                    .filter(f -> f.getBookCode().equals(x.getBookCode()))
                    .findFirst();
            if(amount.isPresent()){
                de.setAmount(amount.get().getAmount());
            }
            de.setPricePerUnit(x.getPricePerUnit());
            de.setTotalPrice(de.getAmount() * de.getPricePerUnit());
            return de;
        }).collect(Collectors.toList()));

        double price = bookList.stream().mapToDouble(d -> {
            Optional<BuyBookDetail> buyAmount = query.getBuy().stream().filter(f -> f.getBookCode().equals(d.getBookCode()))
                    .findFirst();
            if(buyAmount.isPresent()){

                return buyAmount.get().getAmount() * d.getPricePerUnit();
            }
            return 0;
        }).sum();
        response.setPrice(price);
        response.setDiscount(price * calculatePercentDiscount(bookList));
        response.setTotalPrice(response.getPrice() - response.getDiscount());

        return response;
    }

    private List<Catalog> getBookData(List<BuyBookDetail> byBookDetails){
        List<Catalog> bookList = new ArrayList<>();
        for (BuyBookDetail detail : byBookDetails) {
            Catalog b = this.catalogService.getBuyBookCode(detail.getBookCode());
            if(b != null){
                bookList.add(b);
            }
        }
        return bookList;
    }

    private double calculatePercentDiscount(List<Catalog> bookList){
        List<String> tempBookCode = bookList.stream().map(x -> x.getBookCode()).collect(Collectors.toList());
        Map<String, Long> counted = tempBookCode.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        double percentDiscount = 0;
        switch (counted.size()){
            case 2: percentDiscount = 0.05;
                break;
            case 3: percentDiscount = 0.10;
                break;
            case 4: percentDiscount = 0.20;
                break;
            case 5: percentDiscount = 0.25;
                break;
            default:
                percentDiscount = 0;
                break;
        }

        return percentDiscount;
    }
}
