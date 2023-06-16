package com.adme.gestion.sonorisation.adapters.db.repository;

import com.adme.gestion.sonorisation.adapters.db.entities.AssignationConsole;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignationConsoleRepository extends JpaRepository<AssignationConsole, UUID> {

}
