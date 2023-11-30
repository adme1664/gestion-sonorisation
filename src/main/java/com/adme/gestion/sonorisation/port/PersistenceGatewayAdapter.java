package com.adme.gestion.sonorisation.port;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersistenceGatewayAdapter {
  ProclamateurPersistenceAdapter proclamateurPersistenceAdapter;
  ProgrammePersistenceGateway programmePersistenceGateway;

  public PersistenceGatewayAdapter(
      ProclamateurPersistenceAdapter proclamateurPersistenceAdapter,
      ProgrammePersistenceGateway programmePersistenceGateway) {
    this.proclamateurPersistenceAdapter = proclamateurPersistenceAdapter;
    this.programmePersistenceGateway = programmePersistenceGateway;
  }
}
