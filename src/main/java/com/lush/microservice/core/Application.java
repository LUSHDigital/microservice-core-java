package com.lush.microservice.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * Class in which the application is run
 *
 * @author Is
 * @author Jelly
 */
@SpringBootApplication
public class Application {

  /**
   * Run spring boot application.
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}