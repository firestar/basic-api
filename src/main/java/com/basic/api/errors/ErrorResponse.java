package com.basic.api.errors;

public class ErrorResponse{
  String code;
  String reason;

  public ErrorResponse(String code, String reason) {
    this.code = code;
    this.reason = reason;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
