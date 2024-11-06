package com.is.servidor_barrio.business.logic.service;


import com.is.servidor_barrio.business.domain.enumeration.Rol;
import com.is.servidor_barrio.business.logic.error.ErrorServiceException;
import com.is.servidor_barrio.business.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

  @Autowired
  private UsuarioRepository repository;


  public void validar(String email, String clave) throws ErrorServiceException {
    try {

      if (email == null || email.trim().isEmpty()) {
        throw new ErrorServiceException("Debe indicar el Email");
      }

      if (clave == null || clave.trim().isEmpty()) {
        throw new ErrorServiceException("Debe indicar la clave");
      }

    }catch(ErrorServiceException e) {
      throw e;
    }catch(Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }

  }

  @Transactional
  public Usuario save(String email, String clave, Rol rol) throws ErrorServiceException {

    try {

      validar(email, clave);

      Usuario usuario = new Usuario();
      usuario.setEmail(email);
      usuario.setRol(rol);
      usuario.setClave(new BCryptPasswordEncoder().encode(clave));

      return repository.save(usuario);

    }catch(ErrorServiceException e) {
      throw e;

    }catch(Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }
  }

  @Transactional
  public Usuario update(Long idUsuario,String email, String clave) throws ErrorServiceException {

    try {

      validar(email, clave);

      Usuario usuario = findById(idUsuario);
      usuario.setEmail(email);
      usuario.setClave(clave);

      return repository.save(usuario);

    }catch(ErrorServiceException e) {
      throw e;

    }catch(Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }
  }

  @Transactional
  public void delete(Long idUsuario) throws ErrorServiceException {

    try {

      Usuario usuario = findById(idUsuario);
      usuario.setEliminado(true);

      repository.save(usuario);

    }catch(ErrorServiceException e) {
      throw e;

    }catch(Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }

  }

  @Transactional
  public void changeRol(Long idUsuario, Rol rol) throws ErrorServiceException {

    try {

      Usuario usuario = findById(idUsuario);

      usuario.setRol(rol);

      repository.save(usuario);

    }catch(ErrorServiceException e) {
      throw e;

    }catch(Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }

  }

  @Transactional
  public Usuario findById(Long idUsuario) throws ErrorServiceException {

    try {

      if (idUsuario == null) {
        throw new ErrorServiceException("Debe indicar el usuario");
      }

      Optional<Usuario> optional = repository.findById(idUsuario);
      Usuario usuario = null;
      if (optional.isPresent()) {
        usuario= optional.get();
        if (usuario == null || usuario.getEliminado()){
          throw new ErrorServiceException("No se encuentra el usuario indicado");
        }
      }

      return usuario;

    } catch (ErrorServiceException ex) {
      throw ex;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }

  }

  public Usuario findByEmail (String email) throws ErrorServiceException {

    try {

      if (email == null || email.trim().isEmpty()) {
        throw new ErrorServiceException("Debe indicar el email");
      }

      return repository.findByEmail(email);

    } catch (ErrorServiceException ex) {
      throw ex;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }
  }

  public List<Usuario> findAll()throws ErrorServiceException {

    try {

      return repository.findAll();

    }catch(Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }

  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Usuario usuario = repository.findByEmail(email);

    if (usuario != null) {

      List<GrantedAuthority> permisos = new ArrayList();

      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

      permisos.add(p);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

      HttpSession session = attr.getRequest().getSession(true);

      session.setAttribute("usuariosession", usuario);

      return new User(usuario.getEmail(), usuario.getClave(), permisos);

    } else {

      return null;

    }

  }

  public Usuario login(String email, String clave) throws ErrorServiceException {
    try {
      System.out.println("entro a login");
      if (email == null || email.trim().isEmpty()) {
        throw new ErrorServiceException("Debe indicar el usuario");
      }

      if (clave == null || clave.trim().isEmpty()) {
        throw new ErrorServiceException("Debe indicar la clave");
      }

      Usuario usuario = repository.findByEmail(email);

      if (usuario == null) {
        throw new ErrorServiceException("No existe usuario para el correo indicado");
      }

      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      if (!passwordEncoder.matches(clave, usuario.getClave())) {
        throw new ErrorServiceException("Clave incorrecta");
      }

      return usuario;

    } catch (ErrorServiceException e) {
      throw e;

    } catch (Exception e) {
      e.printStackTrace();
      throw new ErrorServiceException("Error del sistema");
    }
  }



}
