package com.stano.flexforce;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

import java.util.TimeZone;

@SpringBootApplication
public class SpringLauncher {
  private static final Logger LOGGER = LoggerFactory.getLogger(SpringLauncher.class);

  public static void main(String[] args) {
    try {
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.println("**************************************************");
        System.out.println("Shutting down...");
        System.out.println("**************************************************");
      }));

      SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
      SpringApplication.run(SpringLauncher.class, args);
    }
    catch (Throwable x) {
      LOGGER.error("Error starting application", x);
    }
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
