package com.udemy.mapstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.mapstruct.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  
}
