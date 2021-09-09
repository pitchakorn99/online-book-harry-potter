package com.example.exam.harrypotter.repository;

import com.example.exam.harrypotter.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    @Query(value = "select * from tbl_catalog where book_code = :bookCode", nativeQuery = true)
    Catalog findByBookCode(@Param("bookCode") String bookCode);
}
