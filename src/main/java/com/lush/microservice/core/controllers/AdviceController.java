package com.lush.microservice.core.controllers;

import com.lush.microservice.core.enums.ResponseStatusType;
import com.lush.microservice.core.exceptions.CoreException;
import com.lush.microservice.core.models.Response;
import com.lush.microservice.core.utils.HttpUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> handlerCoreException(CoreException e) throws Exception {
    Response response = new Response();
    response.setStatus(e.getStatus());
    response.setCode(e.getCode());
    response.setMessage(e.getMessage());

    HttpUtil httpUtil = new HttpUtil();
    return httpUtil.responseException(e);

//    return response;
  }

  /**
   * Method name : handleBadRequest.
   * Description : Bad request exception handler.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public Response handleBadRequest() {
    Response response = new Response();
    response.setStatus(ResponseStatusType.FAIL);
    response.setCode(400);
    response.setMessage("bad request");
    return response;
  }

}
