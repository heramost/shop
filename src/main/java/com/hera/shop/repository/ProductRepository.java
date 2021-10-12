package com.hera.shop.repository;

import com.hera.shop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @NonNull
    List<Product> findAll();
}
