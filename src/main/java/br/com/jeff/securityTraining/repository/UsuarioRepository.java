package br.com.jeff.securityTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import br.com.jeff.securityTraining.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
