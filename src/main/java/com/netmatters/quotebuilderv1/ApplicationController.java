package com.netmatters.quotebuilderv1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

  @GetMapping("/index3")
  public String home(){
    return "index3";
  }

  @GetMapping("/index")
  public String index(){return "index";}

//  @GetMapping("/quotes")
//  public String quotesHome(){
//    return "/quotes/quoteindex";
//  }
//  @GetMapping("/products")
//  public String productsHome(){return "/products/productindex";}
}
