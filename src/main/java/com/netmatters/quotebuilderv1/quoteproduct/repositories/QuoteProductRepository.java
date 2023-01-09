package com.netmatters.quotebuilderv1.quoteproduct.repositories;

import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteProductRepository extends JpaRepository<QuoteProduct, QuoteProductKey> {

  List<QuoteProduct> findAll();
  QuoteProduct save(QuoteProduct quoteProduct);

  Optional<QuoteProduct> findByQuote_Id(Long id);
  QuoteProduct findByQuote_IdAndProduct_Id(Long id1, Long id2);
  List<Optional<QuoteProduct>> findAllByQuote_Id(Long id);
}
