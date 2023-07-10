package com.adme.gestion.sonorisation.port;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersistenceGatewayAdapter {
  ProclamateurPersistenceGateway proclamateurPersistenceGateway;
  ProgrammePersistenceGateway programmePersistenceGateway;

  public PersistenceGatewayAdapter(
      ProclamateurPersistenceGateway proclamateurPersistenceGateway,
      ProgrammePersistenceGateway programmePersistenceGateway) {
    this.proclamateurPersistenceGateway = proclamateurPersistenceGateway;
    this.programmePersistenceGateway = programmePersistenceGateway;
  }
}
