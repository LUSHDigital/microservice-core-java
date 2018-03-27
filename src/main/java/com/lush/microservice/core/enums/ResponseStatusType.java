package com.lush.microservice.core.enums;

/**
 * ResponseStatus
 *
 * Enum class for possible response statuses.
 * (Only OK or FAIL)
 *
 * @author Is
 * @author Jelly
 */
public enum ResponseStatusType {
  OK("ok"), FAIL("fail");

  /**
   *  Response http status.
   */
  private String status;

  /**
   * Default creator
   */
  ResponseStatusType(String status) {
    this.status = status;
  }

  /**
   * Get Response status
   */
  public String getStatus() {
    return status;
  }
}
