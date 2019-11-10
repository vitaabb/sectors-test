package com.sectors.test;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
  @Bean
  public Clock clock() {
    return Clock.systemUTC();
  }
}
