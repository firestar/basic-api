package com.basic.api.rest;

import com.basic.api.models.Product;
import com.basic.api.models.User;
import com.basic.api.services.ProductService;
import com.basic.api.errors.ErrorResponse;
import com.basic.api.validator.UserValidator;
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

import java.util.HashMap;

@RestController
@RequestMapping("/v1")
public class UserController{
  @Autowired
  ProductService productService;
  private static HashMap<String, User> users = new HashMap<>();
  @GetMapping("/user")
  public User getUser(@RequestParam String uuid, HttpServletResponse httpServletResponse){
    User user = users.get(uuid);
    if(user!=null){
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      return user;
    }
    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }

  @PostMapping("/user")
  public Object createUser(@RequestBody User user, HttpServletResponse httpServletResponse){
    if(users.containsKey(user.getUuid())){
      httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return null;
    }
    if(!UserValidator.nameValidator(user.getName())){
      httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return new ErrorResponse("name","User name does not meet requirements.");
    }
    if(!UserValidator.addressValidator(user.getName())){
      httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return new ErrorResponse("name","User address does not meet requirements.");
    }
    // validator
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    users.put(user.getUuid(), user);
    return users.get(user.getUuid());
  }
  @PutMapping("/user")
  public Object modifyUser(@RequestBody User user, HttpServletResponse httpServletResponse){
    User storedUser = users.get(user.getUuid());
    if(storedUser==null){
      httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    boolean changedName = false, changedAddress = false, changedProducts = false;
    if(user.getName()!=null) {
      if(!UserValidator.nameValidator(user.getName())){
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ErrorResponse("name", "User name does not meet requirements.");
      }
      changedName = true;
    }
    if(user.getAddress()!=null) {
      if (!UserValidator.addressValidator(user.getName())) {
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ErrorResponse("name", "User address does not meet requirements.");
      }
      changedAddress = true;
    }

    if(user.getOwnedProducts() !=null ){
      for (Product ownedProduct : user.getOwnedProducts()) {
        Product product = productService.getProductByPUID( ownedProduct.getPuid());
        if(product!=null){
          boolean exists = storedUser.getOwnedProducts().stream().filter(p->p.getPuid().equals(ownedProduct.getPuid())).count()==1;
          if(!exists){
            storedUser.getOwnedProducts().add(product);
            changedProducts = true;
          }
        }

      }
    }
    if(changedName) storedUser.setName(user.getName());
    if(changedAddress) storedUser.setAddress(user.getAddress());
    // validator
    if(changedAddress || changedName || changedProducts) {
      httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
      return storedUser;
    }
    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
    return storedUser;
  }

  @DeleteMapping("/user")
  public User deleteUser(@RequestParam String uuid, HttpServletResponse httpServletResponse){
    User user = users.get(uuid);
    if(user!=null){
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      users.remove(uuid);
      return null;
    }
    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
}
