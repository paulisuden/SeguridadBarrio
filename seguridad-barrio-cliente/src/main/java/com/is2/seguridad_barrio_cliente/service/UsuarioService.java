package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.enumeration.Rol;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.UsuarioDAORest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    public UsuarioDAORest dao;

    public UsuarioDTO  registrar(String correo, String clave, Rol rol) throws ErrorServiceException {
        try {
            //validaciones
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setEmail(correo);
            usuario.setClave(clave);
            usuario.setRol(rol);
            dao.registrar(usuario);
            return usuario;
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO crearUsuario(String correo, String clave, Rol rol) throws ErrorServiceException {
        try {
            //validaciones
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setEmail(correo);
            usuario.setClave(clave);
            usuario.setRol(rol);
            dao.crear(usuario);
            return usuario;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscarCuenta (String cuenta) throws ErrorServiceException {
        try {
            if (cuenta == null) {
                throw new ErrorServiceException("Debe indicar la cuenta");
            }
            UsuarioDTO obj = dao.buscarCuenta(cuenta);
            return obj;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public UsuarioDTO buscarPorIdPersona (String idPersona) throws ErrorServiceException {
        try {
            if (idPersona == null) {
                throw new ErrorServiceException("Debe indicar la persona");
            }
            UsuarioDTO obj = dao.buscarPorIdUsuario(idPersona);
            return obj;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UsuarioDTO usuario = buscarCuenta(username);
            if (usuario != null) {
                System.out.println(usuario);
                List<GrantedAuthority> permisos = new ArrayList();
                GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
                permisos.add(p);

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession(true);
                session.setAttribute("usuariosession", usuario);
                return new User(usuario.getEmail(), " ", permisos);
            } else {
                return null;
            }
        } catch (ErrorServiceException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }


    }
}
