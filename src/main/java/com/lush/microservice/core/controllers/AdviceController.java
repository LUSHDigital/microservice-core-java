package com.lush.microservice.core.controllers;

import com.lush.microservice.core.enums.ExceptionType;
import com.lush.microservice.core.exceptions.CoreException;
import com.lush.microservice.core.models.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdviceController
 * Intercepter Controller.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestController
public class AdviceController {

  /**
   * Method name : handlerCoreException.
   * Description : Core Exception Handler.
   *
   * @return Response
   */
  @ExceptionHandler(CoreException.class)
  public Response handlerCoreException(CoreException e) {
    Response response = new Response();
    response.setStatus(e.getStatus());
    response.setCode(e.getCode());
    response.setMessage(e.getMessage());
    return response;
  }

  /**
   * Method name : handleBadRequest.
   * Description : Bad request exception handler.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public Response handleBadRequest() {
    throw new CoreException().setCommonExceptoin(ExceptionType.BAD_REQEUST);
  }
}
