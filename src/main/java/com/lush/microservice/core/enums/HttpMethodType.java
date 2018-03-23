package com.lush.microservice.core.enums;

/**
 * HttpMethod
 *
 * Enum class for possible http methods.
 *
 * @author Is
 * @author Jelly
 */
public enum HttpMethodType {

  GET("get"), POST("post"), PUT("put"), PATCH("patch"), DELETE("delete");

  /**
   * Define method of http.
   */
  private String method;

  /**
   * Constructor.
   *
   * @param method
   */
  HttpMethodType(String method) {
    this.method = method;
  }

  public String getMethod() {
    return method;
  }
}