package com.adme.gestion.sonorisation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
//@EnableMethodSecurity(proxyTargetClass = true)
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
  @Value("${endpoints.cors.allowed-origins}")
  private String allowedOrigins;


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    log.info("ORIGINS: {}", allowedOrigins);
    registry.addMapping("*")
        //192.168.1.27
        .allowedOrigins(allowedOrigins).maxAge(3600);
  }
}
