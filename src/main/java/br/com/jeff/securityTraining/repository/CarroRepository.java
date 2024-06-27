package br.com.jeff.securityTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jeff.securityTraining.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer>{
}
