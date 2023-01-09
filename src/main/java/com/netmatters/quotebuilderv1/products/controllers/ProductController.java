package com.netmatters.quotebuilderv1.products.controllers;

import com.netmatters.quotebuilderv1.products.models.Product;
import com.netmatters.quotebuilderv1.products.repositories.ProductRepository;
import com.netmatters.quotebuilderv1.products.services.ProductService;
import com.netmatters.quotebuilderv1.quotes.models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<List<Product>> findAllQuotes(){

    List<Product> products = new ArrayList<Product>();
    productService.findAll().forEach(products::add);

    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("products/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
    Product product = productService.findByProductId(id)
        .orElseThrow(()-> new ResourceNotFoundException("No product found with that ID."));

    return new ResponseEntity<>(product, HttpStatus.OK);

  }

  @GetMapping("products/number/{num}")
  public ResponseEntity<Product> getProductByNumber(@PathVariable("num") long num){
    Product product = productService.findByProductId(num)
        .orElseThrow(()-> new ResourceNotFoundException("No product found with that ID."));

    return new ResponseEntity<>(product, HttpStatus.OK);

  }
}
