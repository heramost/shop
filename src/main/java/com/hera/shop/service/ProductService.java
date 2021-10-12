package com.hera.shop.service;

import com.hera.shop.dto.PriceDto;
import com.hera.shop.dto.ProductDto;
import com.hera.shop.model.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductDto productDto);

    List<Product> list();

    Product get(long id);

    Product setPrice(long id, PriceDto priceDto);
}
