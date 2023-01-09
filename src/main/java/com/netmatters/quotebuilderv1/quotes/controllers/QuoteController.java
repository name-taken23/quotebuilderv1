package com.netmatters.quotebuilderv1.quotes.controllers;

import com.netmatters.quotebuilderv1.products.models.Product;
import com.netmatters.quotebuilderv1.quotes.models.Quote;
import com.netmatters.quotebuilderv1.quotes.services.QuoteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuoteController {
  @Autowired
  private QuoteService quoteService;

  //get all quotes
  @GetMapping("/quotes")
  public ResponseEntity<List<Quote>> findAllQuotes(){

    List<Quote> quotes = new ArrayList<Quote>();
    quoteService.findAll().forEach(quotes::add);

    return new ResponseEntity<>(quotes, HttpStatus.OK);
  }
  //Get by quote Id
  @GetMapping("quotes/{id}")
  public ResponseEntity<Quote> getQuoteById(@PathVariable("id") long id){
    Quote quote = quoteService.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("No found quote with that ID."));

    return new ResponseEntity<>(quote, HttpStatus.OK);

  }
  //Get by quote number
  @GetMapping("quotes/number/{num}")
  public ResponseEntity<Quote> getProductByNumber(@PathVariable("num") long num){
    Quote quote = quoteService.findByQuoteNumber(num)
        .orElseThrow(()-> new ResourceNotFoundException("No product found with that ID."));

    return new ResponseEntity<>(quote, HttpStatus.OK);

  }
  //Update a quote
  @PutMapping("quotes/{id}")
  public ResponseEntity<Quote> updateQuote(@PathVariable("id") long id, @RequestBody Quote quote){
    Optional<Quote> quoteData = quoteService.findById(id);
    if(quoteData.isPresent()){
      Quote _quote = quoteData.get();
      _quote.setQuoteName(quote.getQuoteName());
      _quote.setQuoteDescription(quote.getQuoteDescription());
      _quote.setQuoteNumber(quote.getQuoteNumber());
      _quote.setQuoteDate(quote.getQuoteDate());
      return new ResponseEntity<>(quoteService.save(_quote), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  //Create a new quote
  @PostMapping("/quotes")
  public ResponseEntity<Quote> createQuote(@RequestBody Quote quote){
    try{
      Quote _quote = quoteService.save(new Quote(quote.getQuoteName(), quote.getQuoteDescription(), quote.getQuoteNumber(), quote.getQuoteDate()));
      return new ResponseEntity<>(_quote, HttpStatus.CREATED);
    }catch (Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
