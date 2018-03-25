package com.lush.microservice.core.models;

import com.lush.microservice.core.enums.ResponseStatusType;
import com.lush.microservice.core.enums.ResponseStatusType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Response
 *
 * Response object to user requests.
 *
 * @author Is
 * @author Jelly
 */
@Component
public class Response {

  /**
   * Response status.
   */
  private String status;

  /**
   * Response code.
   */
  private Integer code;

  /**
   * Response message.
   */
  private String message;

  /**
   * Response data.
   */
  private Object data;

  /**
   * Default constructor.
   */
  public Response () {
    this.status = ResponseStatusType.OK.getStatus();
    this.code = HttpStatus.OK.value();
    this.message = "success";
    this.data = "";
  }

  /**
   * Constructor.
   *
   * @param status
   * @param code
   * @param message
   */
  public Response(String status, Integer code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.data = "";
  }

  /**
   * Constructor.
   *
   * @param status
   * @param code
   * @param message
   * @param data
   */
  public Response(String status, Integer code, String message, Object data) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}