package com.netmatters.quotebuilderv1.products.models;

//import com.netmatters.quotebuilderv1.item.models.Item;
import com.netmatters.quotebuilderv1.quoteproduct.models.QuoteProduct;
import com.netmatters.quotebuilderv1.quotes.models.Quote;

import net.minidev.json.annotate.JsonIgnore;

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


@Entity
@Table(name="products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name")
  private String productName;
  @Column(name = "product_description")
  private String productDescription;
  @Column(name = "product_price")
  private double productPrice;

  @Column(name ="product_number")
  private int productNumber;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
  Set<QuoteProduct> quoteProduct;


  protected Product(){
    quoteProduct = new HashSet<>();
  }



  public Product(String productName, String productDescription, double productPrice){
    this();
    this.productName = productName;
    this.productDescription = productDescription;
    this.productPrice = productPrice;
  }

  public Long getId() {
    return id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public int getProductNumber() {
    return productNumber;
  }

  public void setProductNumber(int productNumber) {
    this.productNumber = productNumber;
  }

  public Set<QuoteProduct> getQuoteProduct() {
    return quoteProduct;
  }

  public void setQuoteProduct(
      Set<QuoteProduct> quoteProduct) {
    this.quoteProduct = quoteProduct;
  }

  public void addProductToQuoteProduct(QuoteProduct quoteProduct){
    this.quoteProduct.add(quoteProduct);
    quoteProduct.setProduct(this);
  }

  public void removeProductFromQuoteProduct(Product product){
    QuoteProduct quoteProducts = this.quoteProduct.stream().filter(t-> t.getQuoteId() == product.getId()).findFirst().orElse(null);
    if(quoteProducts != null){
      this.quoteProduct.remove(product);
      quoteProducts.getProduct().removeProductFromQuoteProduct(product);
    }
  }
}
