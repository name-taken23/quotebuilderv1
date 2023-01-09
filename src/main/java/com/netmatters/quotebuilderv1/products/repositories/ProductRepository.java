package com.netmatters.quotebuilderv1.products.repositories;

import com.netmatters.quotebuilderv1.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAll();

  Product findByProductNumber(int num);

}
