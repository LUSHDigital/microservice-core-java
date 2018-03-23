package com.lush.microservice.core.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * CoreControllerTest
 *
 * Class to test unit core endpoints.(/, /healthz)
 *
 * @author Is
 * @author Jelly
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoreControllerTest {

  /**
   * MockMvc does not create Servlet Container.
   * You can test the business logic of the API deployed.
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * checkHealthz
   * Test the application health.(application, database, redis)
   *
   * @throws Exception
   */
  @Test
  public void checkHealthz() throws Exception {
    mockMvc.perform(get("/healthz"))
        .andDo(print())
        .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status", is("OK")));
  }

  /**
   * checkEndpoints
   * Test the application service information and mapping endpoints.
   *
   * @throws Exception
   */
  @Test
  public void checkEndpoints() throws Exception {
    mockMvc.perform(get("/"))
        .andDo(print())
        .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
        .andExpect(status().isOk());
  }
}
