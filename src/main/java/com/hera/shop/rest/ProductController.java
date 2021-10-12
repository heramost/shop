package com.hera.shop.rest;

import com.hera.shop.dto.PriceDto;
import com.hera.shop.dto.ProductDto;
import com.hera.shop.model.Product;
import com.hera.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/products", produces = APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Product create(@Valid @RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @GetMapping
    public List<Product> list() {
        return productService.list();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return productService.get(id);
    }

    @PutMapping(path = "/{id}/price", consumes = APPLICATION_JSON_VALUE)
    public Product setPrice(@PathVariable long id, @Valid @RequestBody PriceDto priceDto) {
        return productService.setPrice(id, priceDto);
    }
}
