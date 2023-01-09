package com.netmatters.quotebuilderv1.quoteproduct.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netmatters.quotebuilderv1.products.models.Product;
import com.netmatters.quotebuilderv1.quotes.models.Quote;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class QuoteProduct {

  @EmbeddedId
  QuoteProductKey id;
  @JsonIgnore
  @ManyToOne
  @MapsId("quoteId")
  @JoinColumn
  Quote quote;
  @JsonIgnore
  @ManyToOne
  @MapsId("productId")
  @JoinColumn
  Product product;


  int productQuantity;

  public QuoteProduct(){

  }

  public QuoteProduct(QuoteProductKey id, Quote quote, Product product, int productQuantity) {
    this.id = id;
    this.quote = quote;
    this.product = product;

  }

  public QuoteProductKey getId() {
    return id;
  }

  public void setId(QuoteProductKey id) {
    this.id = id;
  }

  public Quote getQuote() {
    return quote;
  }
  public Long getQuoteId(){
    return this.quote.getId();
  }

  public Long getProductId(){
    return this.product.getId();
  }

  public void setQuote(Quote quote) {
    this.quote = quote;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getProductQuantity() {
    return productQuantity;
  }

  public void setProductQuantity(int productQuantity) {
    this.productQuantity = productQuantity;
  }
}
