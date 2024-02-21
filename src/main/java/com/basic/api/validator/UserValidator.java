package com.basic.api.validator;

public class UserValidator{
  public static boolean nameValidator(String name){
    return (
      name !=null &&
      // regex for acceptable names
      name.matches("([a-zA-Z0-9_]+)") &&
      name.length()>3 &&
      name.length()<25
    );
  }
  public static boolean addressValidator(String address){
    return (
        address !=null &&
        // regex for address
        address.length()>5 &&
        address.length()<100
    );
  }
}
