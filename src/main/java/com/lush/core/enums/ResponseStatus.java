package com.lush.core.enums;

/**
 * ResponseStatus
 *
 * Enum class for possible response statuses.
 *
 * @author Is
 * @author Jelly
 */
public enum ResponseStatus {
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
  ResponseStatus(String status) {
    this.status = status;
  }
}