package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    public UsuarioDAORest dao;

    @Autowired
    private ImagenService imagenService;

    public UsuarioDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar el usuario");
            }

            UsuarioDTO usuario = dao.buscar(id);

            return usuario;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema al buscar el usuario");
        }
    }

    public UsuarioDTO registrar(String correo, String clave, Rol rol) throws ErrorServiceException {
        try {
            // validaciones
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setEmail(correo);
            usuario.setClave(new BCryptPasswordEncoder().encode(clave));
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

    public void eliminar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar el usuario");
            }

            dao.eliminar(id);

        } catch (ErrorServiceException ex) {
            throw ex;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    public UsuarioDTO crearUsuario(
            String correo,
            String clave,
            Rol rol,
            MultipartFile archivoImagen) throws ErrorServiceException {
        try {
            // validaciones
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setEmail(correo);
            usuario.setClave(new BCryptPasswordEncoder().encode(clave));
            usuario.setRol(rol);

            if (archivoImagen != null && archivoImagen.getSize() > 0) {
                ImagenDTO img = imagenService.crear(archivoImagen);
                if (img != null) {
                    usuario.setImagen(img);
                    usuario.setImagenId(img.getId());
                }
            }
            dao.crear(usuario);
            return usuario;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(
            String id,
            String correo,
            String clave,
            Rol rol,
            MultipartFile archivoImagen) throws ErrorServiceException {

        try {
            // validaciones
            UsuarioDTO usuario = buscar(id);
            usuario.setEmail(correo);
            usuario.setClave(new BCryptPasswordEncoder().encode(clave));
            usuario.setRol(rol);

            if (archivoImagen != null && archivoImagen.getSize() > 0) {
                if (usuario.getImagen() == null) {
                    ImagenDTO imagen = imagenService.crear(archivoImagen);
                    usuario.setImagen(imagen);
                } else
                    imagenService.modificar(usuario.getImagen().getId(), archivoImagen);

                usuario.setImagenId(usuario.getImagen().getId());
            }
            dao.actualizar(usuario);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscarCuenta(String cuenta) throws ErrorServiceException {
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

    public UsuarioDTO buscarPorIdPersona(String idPersona) throws ErrorServiceException {
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
                List<GrantedAuthority> permisos = new ArrayList();
                GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
                permisos.add(p);

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes();
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

    public List<UsuarioDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
