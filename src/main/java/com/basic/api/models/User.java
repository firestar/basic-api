package com.basic.api.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class User{
  String uuid = UUID.randomUUID().toString();
  String name;
  String address;

  private List<Product> ownedProducts = new LinkedList<>();

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Product> getOwnedProducts() {
    return ownedProducts;
  }

  public void setOwnedProducts(List<Product> ownedProducts) {
    this.ownedProducts = ownedProducts;
  }
}
