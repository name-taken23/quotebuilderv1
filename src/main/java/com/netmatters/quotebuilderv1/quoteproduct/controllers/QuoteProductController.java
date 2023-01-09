package com.netmatters.quotebuilderv1.quoteproduct.controllers;

import com.netmatters.quotebuilderv1.products.models.Product;
import com.netmatters.quotebuilderv1.products.services.ProductService;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProductKey;
import com.netmatters.quotebuilderv1.quoteproduct.services.QuoteProductService;
import com.netmatters.quotebuilderv1.quotes.models.Quote;
import com.netmatters.quotebuilderv1.quotes.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuoteProductController {
  @Autowired
  private QuoteProductService quoteProductService;

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private ProductService productService;


  @GetMapping("/quoteproducts")
  public ResponseEntity<List<QuoteProduct>> findAllQuoteProducts(Pageable page){

    List<QuoteProduct> quoteProducts = new ArrayList<QuoteProduct>();
    quoteProductService.findAll().forEach(quoteProducts::add);

    return new ResponseEntity<>(quoteProducts, HttpStatus.OK);
  }

  //find by quote id and product id
  @GetMapping("quoteproducts/{id1}/{id2}")
  public ResponseEntity<QuoteProduct> findAllQuoteProducts(@PathVariable("id1") long id1, @PathVariable("id2") long id2){
    QuoteProduct quoteProduct = quoteProductService.findByQuote_IdAndProduct_Id(id1, id2);
    return new ResponseEntity<>(quoteProduct, HttpStatus.OK);
  }
  //find all by quote id
  @GetMapping("quoteproducts/{id}")
  public ResponseEntity<List<Optional<QuoteProduct>>> getQuoteProductById(@PathVariable("id") long id){
    List<Optional<QuoteProduct>> quoteProducts = new ArrayList<>();
    quoteProductService.findAllByQuote_Id(id).forEach(quoteProducts::add);


    return new ResponseEntity<>(quoteProducts, HttpStatus.OK);

  }

  //Create a new quote product link
  @PostMapping("/quoteproducts")
  public ResponseEntity<QuoteProduct> addProductsToQuote(@RequestBody QuoteProductKey quoteProductKey){

    Optional<Quote> quote = quoteService.findById(quoteProductKey.getQuoteId());
    Optional<Product> product = productService.findByProductId(quoteProductKey.getProductId());
    QuoteProduct quoteProduct = new QuoteProduct(quoteProductKey, quote.get(), product.get(), 1);
    quoteProductService.save(quoteProduct);


    return new ResponseEntity<>(quoteProduct, HttpStatus.CREATED);

    }

    @PutMapping("/updatequoteproduct/{id1}/{id2}")
  public ResponseEntity<QuoteProduct> updateQuoteProduct(@PathVariable("id1") long id1, @PathVariable("id2") long id2, @RequestBody QuoteProduct quoteProduct){
    QuoteProduct _quoteProduct = quoteProductService.findByQuote_IdAndProduct_Id(id1, id2);

      if(_quoteProduct!=null){
        _quoteProduct.setProductQuantity(quoteProduct.getProductQuantity());
        return new ResponseEntity<>(quoteProductService.save(_quoteProduct), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
}




