package com.hera.shop.service.impl;

import com.hera.shop.dto.ProductDto;
import com.hera.shop.model.Product;
import com.hera.shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void productCreated() {
        //arrange
        when(productRepository.save(any(Product.class))).then(returnsFirstArg());
        ProductDto productDto = new ProductDto();
        productDto.setName("PRODUCT_NAME");
        productDto.setPriceInUsd(123.12);

        //act
        Product product = productService.create(productDto);

        //assert
        assertThat(product.getName()).isEqualTo("PRODUCT_NAME");
        assertThat(product.getPriceInUsd()).isEqualTo(123.12);
    }

    @Test
    public void productsListed() {
        //arrange
        List<Product> products = asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        //act
        List<Product> productsReturned = productService.list();

        //assert
        assertThat(productsReturned).isEqualTo(products);
    }

    @Test
    public void productFound() {
        //arrange
        Optional<Product> optionalProduct = of(new Product());
        when(productRepository.findById(1L)).thenReturn(optionalProduct);

        //act
        Product product = productService.get(1L);

        //assert
        assertThat(product).isEqualTo(optionalProduct.get());
    }

    @Test
    public void productNotFound() {
        //arrange
        when(productRepository.findById(1L)).thenReturn(empty());

        //act, assert
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> productService.get(1L));

        //assert
        assertThat(responseStatusException.getStatus()).isEqualTo(NOT_FOUND);
        assertThat(responseStatusException.getReason()).isEqualTo("Product not found");
    }
}