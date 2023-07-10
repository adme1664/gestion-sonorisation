package com.adme.gestion.sonorisation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Slf4j
public class GestionProgrammeConfiguration {

  @Bean("auditorProvider")
  public AuditorAware<String> auditorProvider(){
    log.info("creating auditor provider...");
    return new AuditingAwareConfiguration();
  }

}
