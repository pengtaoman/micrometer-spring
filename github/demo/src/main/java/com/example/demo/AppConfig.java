package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import io.micrometer.core.instrument.MeterRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = AppConfig.class)
class AppConfig {
  @Bean
  MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(
          @Value("${spring.application.name}") String applicationName) {
    return registry -> registry.config().commonTags("application", applicationName);
  }
}
