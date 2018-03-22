package com.lush.core.enums;

/**
 * HttpMethod
 *
 * Enum class for possible http methods.
 *
 * @author Is
 * @author Jelly
 */
public enum HttpMethod {

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
  HttpMethod(String method) {
    this.method = method;
  }
}