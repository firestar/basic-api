package com.basic.api.rest;

import com.basic.api.models.Product;
import com.basic.api.models.User;
import com.basic.api.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ProductController{
  @Autowired
  ProductService productService;


  @GetMapping("/product")
  public Product getProduct(@RequestParam String puid, HttpServletResponse httpServletResponse){
    Product product = productService.getProductByPUID(puid);
    if(product!=null){
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      return product;
    }
    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }

  @PostMapping("/product")
  public Object createProduct(@RequestBody Product product, HttpServletResponse httpServletResponse){
    Product storedProduct = productService.getProductByPUID(product.getPuid());
    if(storedProduct!=null){
      httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return null;
    }
    // validator goes here
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    return productService.create(product);
  }
  @PutMapping("/product")
  public Object modifyProduct(@RequestBody Product product, HttpServletResponse httpServletResponse){
    Product storedProduct = productService.getProductByPUID(product.getPuid());
    if(storedProduct==null){
      httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    storedProduct.setName(product.getName());
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    return storedProduct;
  }

  @DeleteMapping("/product")
  public User deleteUser(@RequestParam String puid, HttpServletResponse httpServletResponse){
    Product storedProduct = productService.getProductByPUID(puid);
    if(storedProduct!=null){
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      productService.delete(storedProduct);
      return null;
    }
    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }

}
