package com.example.exam.harrypotter.controller;

import com.example.exam.harrypotter.dtos.responses.CatalogResponse;
import com.example.exam.harrypotter.entity.Catalog;
import com.example.exam.harrypotter.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog")
    public List<CatalogResponse> getAllCatalog(){
        List<CatalogResponse> catalogResponses = new ArrayList<>();

        catalogResponses = this.catalogService.getCatalog().stream().map(m -> {
            CatalogResponse r = new CatalogResponse();
            r.setId(m.getId());
            r.setName(m.getBookName());
            r.setPrice(m.getPricePerUnit());
            r.setBalance(m.getBalance());
            return r;
        }).collect(Collectors.toList());

        return catalogResponses;
    }

    @GetMapping("/catalog/{id}")
    public CatalogResponse getCatalogById(@PathVariable String id){
        Catalog res = this.catalogService.getCatalogById(Integer.parseInt(id));

        CatalogResponse catalogResponse = new CatalogResponse();
        catalogResponse.setId(res.getId());
        catalogResponse.setName(res.getBookName());
        catalogResponse.setPrice(res.getPricePerUnit());
        catalogResponse.setBalance(res.getBalance());
        return catalogResponse;
    }
}
