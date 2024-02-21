package com.basic.api.models;

import java.util.UUID;

public class Product{
  String puid = UUID.randomUUID().toString();
  String name;

  public String getPuid() {
    return puid;
  }

  public void setPuid(String puid) {
    this.puid = puid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
