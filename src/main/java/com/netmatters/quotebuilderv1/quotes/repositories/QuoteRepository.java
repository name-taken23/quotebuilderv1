package com.netmatters.quotebuilderv1.quotes.repositories;

import com.netmatters.quotebuilderv1.quotes.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

  List<Quote> findAll();

  Optional<Quote> findByQuoteNumber(long num);
}

