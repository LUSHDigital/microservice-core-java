package com.lush.microservice.core.models;

import com.lush.microservice.core.enums.ResponseStatusType;
import com.lush.microservice.core.enums.ResponseStatusType;
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
  private ResponseStatusType status;

  /**
   * Response message.
   */
  private String message;

  /**
   * Response data.
   */
  private Object data;

  /**
   * Description : Default constructor.
   */
  public Response () {
    this.status = ResponseStatusType.OK;
    this.message = "";
    this.data = "";
  }

  /**
   * Description : Constructor.
   *
   * @param status
   */
  public Response(ResponseStatusType status) {
    this.status = status;
    this.message = "";
    this.data = "";
  }

  /**
   * Description : Constructor.
   *
   * @param status
   * @param message
   */
  public Response(ResponseStatusType status, String message) {
    this.status = status;
    this.message = message;
    this.data = "";
  }

  // Getter and Setter
  public ResponseStatusType getStatus() {
    return status;
  }

  public void setStatus(ResponseStatusType status) {
    this.status = status;
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