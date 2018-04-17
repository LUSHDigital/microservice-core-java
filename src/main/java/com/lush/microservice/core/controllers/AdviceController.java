package com.lush.microservice.core.controllers;

import com.lush.microservice.core.exceptions.CoreException;
import com.lush.microservice.core.models.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdviceController
 *
 * Intercepter Controller.
 *
 * @author With
 */
@ControllerAdvice
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
}
