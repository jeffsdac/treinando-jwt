package br.com.jeff.securityTraining.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

import br.com.jeff.securityTraining.entities.Role;
import br.com.jeff.securityTraining.entities.Usuario;
import br.com.jeff.securityTraining.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("O email informado não corresponde a nenhum usuario"));

        var usuarioAuthorities = mapRolesToAuthorities(usuario.getRoles());

        return new User(usuario.getEmail(), usuario.getPassword(), usuarioAuthorities);
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities (List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
    }

}
