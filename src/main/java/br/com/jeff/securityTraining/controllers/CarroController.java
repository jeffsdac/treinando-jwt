package br.com.jeff.securityTraining.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jeff.securityTraining.Dto.CarroDtoRegistrar;
import br.com.jeff.securityTraining.entities.Carro;
import br.com.jeff.securityTraining.repository.CarroRepository;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    CarroRepository carroRepo;
    
    @PostMapping
    public ResponseEntity<String> registerCarro(@RequestBody CarroDtoRegistrar carroDto) {
        var carroRegistrar = new Carro();
        carroRegistrar.setPlaca(carroDto.placa());

        carroRepo.save(carroRegistrar);

        return ResponseEntity.status(HttpStatus.OK).body("CARRO REGISTRADO");
    }
}
