package com.lush.core.models;

import com.lush.core.enums.ResponseStatus;
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
  private ResponseStatus status;

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
    this.status = ResponseStatus.OK;
    this.message = "";
    this.data = "";
  }

  /**
   * Description : Constructor.
   *
   * @param status
   */
  public Response(ResponseStatus status) {
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
  public Response(ResponseStatus status, String message) {
    this.status = status;
    this.message = message;
    this.data = "";
  }

  // Getter and Setter
  public ResponseStatus getStatus() {
    return status;
  }

  public void setStatus(ResponseStatus status) {
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