package com.lush.microservice.core.models;

import org.springframework.stereotype.Component;

/**
 * Class with cache information.
 *
 * @author Is
 */
@Component
public class Cache {

  /**
   * Cache key.
   */
  private String key;

  /**
   * Cache hash key, this value should be the only one.
   */
  private String hashKey;

  /**
   * Cache value.
   */
  private Object value;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getHashKey() {
    return hashKey;
  }

  public void setHashKey(String hashKey) {
    this.hashKey = hashKey;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
