package br.com.jeff.securityTraining.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jeff.securityTraining.Dto.LoginDto;
import br.com.jeff.securityTraining.Dto.RegisterDto;
import br.com.jeff.securityTraining.Dto.ResponseAuthDto;

import java.util.Collections;
import br.com.jeff.securityTraining.entities.Role;
import br.com.jeff.securityTraining.entities.Usuario;
import br.com.jeff.securityTraining.repository.RoleRepository;
import br.com.jeff.securityTraining.repository.UsuarioRepository;
import br.com.jeff.securityTraining.security.TokenGenerator;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passEncoder;

    @Autowired
    private TokenGenerator jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.email())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(registerDto.email());
        usuario.setPassword(passEncoder.encode(registerDto.password()));

        Role role = roleRepo.findByName("USER").get();
        usuario.setRoles(Collections.singletonList(role));

        userRepo.save(usuario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthDto> login (@RequestBody LoginDto loginDto){
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAuthDto(token));
    }
}
