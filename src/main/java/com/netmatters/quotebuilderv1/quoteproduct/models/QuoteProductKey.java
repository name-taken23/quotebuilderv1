package com.netmatters.quotebuilderv1.quoteproduct.models;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class QuoteProductKey implements Serializable {

  @Column(name = "quote_id")
  Long quoteId;

  @Column(name = "product_id")
  Long productId;


  public QuoteProductKey(){

  }
  public QuoteProductKey(Long quoteId, Long productId) {
    this.quoteId = quoteId;
    this.productId = productId;
  }

  public Long getQuoteId() {
    return quoteId;
  }

  public void setQuoteId(Long quoteId) {
    this.quoteId = quoteId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    QuoteProductKey quoteProductKey = (QuoteProductKey) o;

    if (!quoteId.equals(quoteProductKey.quoteId)) {
      return false;
    }
    return Objects.equals(productId, quoteProductKey.productId);
  }

  @Override
  public int hashCode() {
    int result = quoteId.hashCode();
    result = 31 * result + (productId != null ? productId.hashCode() : 0);
    return result;
  }
}
