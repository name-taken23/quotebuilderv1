package com.netmatters.quotebuilderv1.quoteproduct.services;

import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProductKey;
import com.netmatters.quotebuilderv1.quoteproduct.repositories.QuoteProductRepository;
import com.netmatters.quotebuilderv1.quotes.models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteProductService {
  @Autowired
  private QuoteProductRepository quoteProductRepository;

  public QuoteProduct save(QuoteProduct quoteProduct){ return quoteProductRepository.save(quoteProduct);}

  public List<QuoteProduct> findAll(){
    return quoteProductRepository.findAll();
  }

  public Optional<QuoteProduct> findByQuote_Id(long id){return quoteProductRepository.findByQuote_Id(id);}
  public QuoteProduct findByQuote_IdAndProduct_Id(long id1, long id2){return quoteProductRepository.findByQuote_IdAndProduct_Id(id1, id2);}
  public List<Optional<QuoteProduct>> findAllByQuote_Id(long id){return quoteProductRepository.findAllByQuote_Id(id);}


  public QuoteProduct updateQuantity(Long id1, Long id2, QuoteProduct quoteProduct){
    QuoteProduct _quoteProduct = quoteProductRepository.findByQuote_IdAndProduct_Id(id1, id2);
    _quoteProduct.setProductQuantity(quoteProduct.getProductQuantity());
    quoteProductRepository.save(quoteProduct);
  return _quoteProduct;
  }
}
