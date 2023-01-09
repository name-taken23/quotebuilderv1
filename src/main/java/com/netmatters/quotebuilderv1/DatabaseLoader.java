package com.netmatters.quotebuilderv1;


import com.netmatters.quotebuilderv1.products.models.Product;
import com.netmatters.quotebuilderv1.products.services.ProductService;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProductKey;
import com.netmatters.quotebuilderv1.quoteproduct.services.QuoteProductService;
import com.netmatters.quotebuilderv1.quotes.models.Quote;
import com.netmatters.quotebuilderv1.quotes.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DatabaseLoader implements ApplicationRunner {
  private final QuoteService quoteService;

  private final QuoteProductService quoteProductService;

  private final ProductService productService;

  @Autowired
  public DatabaseLoader(QuoteService quoteService, QuoteProductService quoteProductService,
                        ProductService productService) {
    this.quoteService = quoteService;
    this.quoteProductService = quoteProductService;
    this.productService = productService;

  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

//    Optional<Quote> quoteOptional = quoteService.findById(2L);
//    Quote currentQuote = quoteOptional.get();
//    Optional<Product> productOptional = productService.findByProductId(4L);
//    Product product = productOptional.get();
//    QuoteProductKey quoteProductKey = new QuoteProductKey(currentQuote.getId(), product.getId());
//    QuoteProduct qp = new QuoteProduct(quoteProductKey, currentQuote, product, 2);
//    quoteProductService.save(qp);
  }
}