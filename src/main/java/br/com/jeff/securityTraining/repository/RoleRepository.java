package br.com.jeff.securityTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import br.com.jeff.securityTraining.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(String name);
}
