package com.lush.microservice.core.models;

import org.springframework.http.HttpMethod;

/**
 * Endpoint
 *
 * Endpoint information.
 *
 * @author Is
 * @author Jelly
 */
public class Endpoint {

  /**
   * Endpoint uri.
   */
  private String uri;

  /**
   * Http protocol method.
   */
  private HttpMethod method;

  // Getter and Setter
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public void setMethod(HttpMethod method) {
    this.method = method;
  }
}
