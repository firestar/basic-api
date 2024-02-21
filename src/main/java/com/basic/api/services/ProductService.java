package com.basic.api.services;

import com.basic.api.models.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProductService{
  private HashMap<String, Product> products;

  public ProductService() {
    this.products = new HashMap<>();
  }

  public Product getProductByPUID(String puid) {
    return products.get(puid);
  }
  public Product create(Product product){
    products.put(product.getPuid(), product);
    return product;
  }
  public void delete(Product product){
    products.remove(product.getPuid());
  }
}
