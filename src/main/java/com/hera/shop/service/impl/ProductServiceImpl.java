package com.hera.shop.service.impl;

import com.hera.shop.dto.PriceDto;
import com.hera.shop.dto.ProductDto;
import com.hera.shop.model.Product;
import com.hera.shop.repository.ProductRepository;
import com.hera.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public Product create(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPriceInUsd(productDto.getPriceInUsd());

        return productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public Product get(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @Override
    public Product setPrice(long id, PriceDto priceDto) {
        Product product = get(id);
        product.setPriceInUsd(priceDto.getPriceInUsd());

        return product;
    }
}
