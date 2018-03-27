package com.lush.microservice.core.enums;

/**
 * Exception Handler
 *
 * @author Jelly
 * @author Is
 */
public enum ExceptionType {

  /**
   * Not Found Data Exception
   */
  NOT_FOUND_DATA(204, "no data found"),

  /**
   * Duplicated Data Exception
   */
  DUPLICATED_DATA(412, "already duplicated data"),

  /**
   * Don't allow File type Exception
   */
  FILE_TYPE_EXCEPTION(415, "don't allow File type");

  /**
   * Exception Code
   */
  private final int code;

  /**
   * Exception Message
   */
  private final String massage;

  /**
   * Default creator
   *
   * @param code
   * @param massage
   */
  ExceptionType(int code, String massage) {
    this.code = code;
    this.massage = massage;
  }

  public int getCode() {
    return code;
  }

  public String getMassage() {
    return massage;
  }

}