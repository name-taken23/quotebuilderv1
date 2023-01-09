package com.netmatters.quotebuilderv1.quotes.services;

import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;
import com.netmatters.quotebuilderv1.quotes.models.Quote;
import com.netmatters.quotebuilderv1.quotes.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

  @Autowired
  private QuoteRepository quoteRepository;

  //find all
  public List<Quote> findAll(){return quoteRepository.findAll();}
  //save quote
  public Quote save(Quote quote){ return quoteRepository.save(quote);}
  //find by ID
  public Optional<Quote> findById(long id){return quoteRepository.findById(id);}
  //find by quote number
  public Optional<Quote> findByQuoteNumber(long num) {return quoteRepository.findByQuoteNumber(num);
  }
}
