package com.is.servidor_barrio.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.is.servidor_barrio.business.logic.error.ErrorServiceException;
import com.is.servidor_barrio.business.logic.service.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var user = userService.buscarPorEmail(username).orElseThrow();
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());
            permisos.add(p);
            return UserPrincipal.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .authorities(permisos)
                    .password(user.getClave())
                    .build();

        } catch (ErrorServiceException e) {
            throw new RuntimeException(e);
        }
    }
}