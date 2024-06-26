package br.com.jeff.securityTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jeff.securityTraining.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
}
