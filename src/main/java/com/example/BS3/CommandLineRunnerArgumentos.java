package com.example.BS3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerArgumentos  implements CommandLineRunner {

  Logger logger = LoggerFactory.getLogger(Bs3Application.class);
  @Override
  public void run(String... args) throws Exception {
    logger.info("Soy la tercera clase");
  }
}
