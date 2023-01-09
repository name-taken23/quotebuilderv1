package com.netmatters.quotebuilderv1.quotes.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;

import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "quotes")
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="quote_name")
  private String quoteName;
  @Column(name = "quote_description")
  private String quoteDescription;
  @Column(name = "quote_number")
  private int quoteNumber;

  @Column(name = "quote_date")
  @Temporal(TemporalType.DATE)
  Date quoteDate;


  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "quote")
  Set<QuoteProduct> quoteProduct;


  protected Quote(){
    quoteProduct = new HashSet<>();
  }

  public Quote(String quoteName, String quoteDescription, int quoteNumber, Date quoteDate){
    this();
    this.quoteName = quoteName;
    this.quoteDescription = quoteDescription;
    this.quoteNumber = quoteNumber;
    this.quoteDate = quoteDate;
  }

  public Long getId() {
    return id;
  }

  public String getQuoteName() {
    return quoteName;
  }

  public void setQuoteName(String quoteName) {
    this.quoteName = quoteName;
  }

  public String getQuoteDescription() {
    return quoteDescription;
  }

  public void setQuoteDescription(String quoteDescription) {
    this.quoteDescription = quoteDescription;
  }

  public int getQuoteNumber() {
    return quoteNumber;
  }

  public void setQuoteNumber(int quoteNumber) {
    this.quoteNumber = quoteNumber;
  }

  public Set<QuoteProduct> getQuoteProduct() {
    return quoteProduct;
  }

  public Date getQuoteDate() {
    return quoteDate;
  }

  public void setQuoteDate(Date quoteDate) {
    this.quoteDate = quoteDate;
  }

  public void setQuoteProduct(
      Set<QuoteProduct> quoteProduct) {
    this.quoteProduct = quoteProduct;
  }

  public void addQuoteToQuoteProduct(QuoteProduct quoteProduct){
    this.quoteProduct.add(quoteProduct);
    quoteProduct.setQuote(this);
  }

  public void removeQuoteFromQuoteProduct(Quote quote){
    QuoteProduct quoteProducts = this.quoteProduct.stream().filter(t-> t.getQuoteId() == quote.getId()).findFirst().orElse(null);
    if(quoteProducts != null){
      this.quoteProduct.remove(quote);
      quoteProducts.getQuote().removeQuoteFromQuoteProduct(quote);
    }
  }

}
