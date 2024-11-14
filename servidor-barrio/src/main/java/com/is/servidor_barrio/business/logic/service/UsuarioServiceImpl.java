package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.logic.error.ErrorServiceException;
import com.is.servidor_barrio.business.repository.BaseRepository;
import com.is.servidor_barrio.business.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, String> implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(BaseRepository<Usuario,String> baseRepository, UsuarioRepository usuarioRepository) {
        super(baseRepository); 
        this.repository = usuarioRepository;
    }

    public Usuario crear(Usuario usuario) {
        usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));
        return repository.save(usuario);
}

    public Usuario searchByCuenta(String cuenta) throws Exception {
        try {
            Usuario usuario = repository.findByEmailAndEliminadoFalse(cuenta);
            return usuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Usuario searchByIdPersona(String idPersona) throws Exception {
        try {
            Usuario usuario = repository.findByPersonaId(idPersona);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario searchByCuentaAndClave(String cuenta, String clave) throws Exception {
        try {
            Usuario usuario = repository.findByEmailAndClaveAndEliminadoFalse(cuenta, clave);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean validar(Usuario entity, String caso) throws Exception {
        try {
            if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el mail");
            }

            if (entity.getClave() == null || entity.getClave().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la clave");
            }

            if (entity.getRol() == null) {
                throw new ErrorServiceException("Debe indicar el rol");
            }

            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El usuario ya existe en el sistema");
                }
                if (repository.findByEmailAndClaveAndEliminadoFalse(entity.getEmail(), entity.getClave()) != null) {
                    throw new ErrorServiceException("El usuario ya existe en el sistema");
                }
            } else {
                Optional<Usuario> uu = repository.findByIdAndEliminadoFalse(entity.getId());
                if (uu.isPresent()) {
                    Usuario e = uu.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El empleado especificado no existe en el sistema");
                    }
                }
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }
    }

    public Optional<Usuario> buscarPorEmail(String email) throws ErrorServiceException {
        try {
            Usuario usuario = repository.findByEmailAndEliminadoFalse(email);
            if (usuario == null) {
                return Optional.empty();
            }
            return Optional.of(usuario);
        } catch (Exception ex) {
            throw new ErrorServiceException("Error encontrando el usuario");
        }
    }

}
