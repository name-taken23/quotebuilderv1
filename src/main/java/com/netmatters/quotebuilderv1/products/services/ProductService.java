package com.netmatters.quotebuilderv1.products.services;

import com.netmatters.quotebuilderv1.products.models.Product;
import com.netmatters.quotebuilderv1.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public List<Product> findAll(){
    return productRepository.findAll();
  }

  public Optional<Product> findByProductId(long id){ return productRepository.findById(id);}
  public Product findByProductNumber(int num){return productRepository.findByProductNumber(num);}
}
