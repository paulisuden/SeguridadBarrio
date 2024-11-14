package com.is2.seguridad_barrio_cliente.security;

import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.AuthService;
import com.is2.seguridad_barrio_cliente.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthService authService;
    private final UsuarioService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username == null || password == null) {
            throw new AuthenticationServiceException("Usuario o contraseña faltante");
        }



        UsuarioDTO user = new UsuarioDTO();
        user.setClave(password);
        user.setEmail(username);

        try {

            String token = authService.authenticateWithApi(user);
            if (token == null) {
                throw new AuthenticationServiceException("Usuario o contraseña incorrecta");
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            UserDetails userDetails = userService.loadUserByUsername(username);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    token,
                    userDetails.getAuthorities());
            return auth;
        } catch (ErrorServiceException e) {
            throw new AuthenticationServiceException("Error del servicio: " + e.getMessage(), e);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException("Error de autenticación: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
