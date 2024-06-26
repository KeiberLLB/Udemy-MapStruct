package com.udemy.mapstruct.test;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.mapstruct.dto.GetProduct;
import com.udemy.mapstruct.entity.Product;
import com.udemy.mapstruct.mapper.ProductMapper;
import com.udemy.mapstruct.repository.ProductRepository;

@Configuration
public class InitDatabase {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductMapper productMapper;

  // private ProductMapper productMapper = ProductMapper.INSTANCE;
  // cuando no hay dependencias

  @Bean
  public CommandLineRunner testProductMapperCommand() {
    return args -> {
      List<Product> products = productRepository.findAll();
      System.out.println("PRODUCTS");
      products.forEach(System.out::println);

      System.out.println("GET PRODUCTS");
      List<GetProduct> getProductList = products.stream().map(product -> productMapper.productToGetDTO(product))
          .peek(System.out::println)
          .collect(Collectors.toList());

      System.out.println("GET PRODUCTS MAPPER");
      List<GetProduct> getProductListMapper = productMapper.toGetProductList(products);
      getProductListMapper.forEach(System.out::println);

      System.out.println("PRODUCTS MAPPER");
      List<Product> mappeProducts = productMapper.toEntityList(getProductList);
      mappeProducts.forEach(System.out::println);
    };
  }
}
