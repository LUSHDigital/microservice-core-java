package com.lush.microservice.core.enums;

/**
 * ResponseStatus
 *
 * Enum class for possible response statuses.
 *
 * @author Is
 * @author Jelly
 */
public enum ResponseStatusType {
  OK("ok")
  , FAIL("fail");

  /**
   * Define status of response.
   */
  private String status;

  /**
   * Constructor
   *
   * @param status
   */
  ResponseStatusType(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}