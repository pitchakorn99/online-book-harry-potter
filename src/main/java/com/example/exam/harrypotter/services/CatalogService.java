package com.example.exam.harrypotter.services;

import com.example.exam.harrypotter.entity.Catalog;
import com.example.exam.harrypotter.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(
            CatalogRepository catalogRepository
    ){
        this.catalogRepository = catalogRepository;
    }

    public List<Catalog> getCatalog(){
        List<Catalog> catalogs = this.catalogRepository.findAll();
        return catalogs;
    }

    public Catalog getCatalogById(int id){
        return this.catalogRepository.getById(id);
    }

    public Catalog getBuyBookCode(String bookCode){
        return this.catalogRepository.findByBookCode(bookCode);
    }
}
