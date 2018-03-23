package com.lush.microservice.core.models;

import com.lush.microservice.core.enums.HttpMethodType;
import com.lush.microservice.core.enums.HttpMethodType;

/**
 * EndpointDto
 *
 * Endpoint's methods and uri information.
 *
 * @author Is
 * @author Jelly
 */
public class EndpointDto {

  public static class Info {

    /**
     * Endpoint uri.
     */
    private String uri;

    /**
     * Http protocol method.
     */
    private HttpMethodType method;

    // Getter and Setter
    public String getUri() {
      return uri;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }

    public HttpMethodType getMethod() {
      return method;
    }

    public void setMethod(HttpMethodType method) {
      this.method = method;
    }
  }
}