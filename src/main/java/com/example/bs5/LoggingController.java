package com.example.bs5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
  private static final Logger LOG = LoggerFactory.getLogger(Bs5Application.class);
  @GetMapping(value="/")
  public String test(){
    LOG.debug("debug message");
    LOG.info("info message");
    LOG.error("error in application");
    LOG.warn("warning in application");
    return "test";
  }

}
