package com.is.servidor_barrio.business.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioCreateDTO;
import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioDTO;
import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.error.ErrorServiceException;
import com.is.servidor_barrio.business.logic.service.UsuarioService;
import com.is.servidor_barrio.business.logic.service.UsuarioServiceImpl;
import com.is.servidor_barrio.business.mapper.UsuarioMapper;
@Service

public class UsuarioFacadeImpl extends BaseFacadeImpl<Usuario, UsuarioDTO, UsuarioCreateDTO, UsuarioCreateDTO, String> {

    protected final UsuarioService baseService;
    protected final UsuarioMapper baseMapper;

    @Autowired
    public UsuarioFacadeImpl(UsuarioService baseService, UsuarioMapper baseMapper) {
        super(baseService, baseMapper);
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    /*
    public UsuarioDTO searchByCuenta(String cuenta) throws Exception {
        Optional<Usuario> usuario = baseService.searchByCuenta(cuenta);
        
        // Verifica si el usuario está presente y conviértelo a DTO, o lanza una excepción si no está
        return usuario
                .map(baseMapper::toDTO) // Llama a toDTO solo si el usuario está presente
                .orElseThrow(() -> new Exception("Usuario no encontrado con la cuenta: " + cuenta));
    }
    
    
    public UsuarioDTO searchByCuentaAndClave(String cuenta, String clave) throws Exception {
        Optional<Usuario> usuario = baseService.buscarPorEmail(cuenta);
    
        // Verifica si el usuario existe y que la clave sea correcta
        return usuario
                .filter(u -> u.getClave().equals(clave)) // Filtra por clave si el usuario está presente
                .map(baseMapper::toDTO) // Convierte a DTO si el usuario está presente y la clave coincide
                .orElseThrow(() -> new Exception("Usuario no encontrado o clave incorrecta"));
    }*/

    public UsuarioDTO crear(UsuarioCreateDTO usuarioCreateDTO) throws Exception {
        try {
            Usuario usuarioEntity = baseMapper.toEntityCreate(usuarioCreateDTO);
            // Guarda la entidad Usuario en la base de datos
            Usuario usuarioGuardado = baseService.save(usuarioEntity);
            // Convierte la entidad guardada en un DTO para retornar
            return baseMapper.toDTO(usuarioGuardado);
        } catch (Exception e) {
            throw new Exception("Error inesperado al guardar el usuario (en la facade)", e);
        }
    }

    public UsuarioDTO searchByIdPersona(String idPersona) throws Exception {
        try {
            Usuario usuario = baseService.searchByIdPersona(idPersona);
            return baseMapper.toDTO(usuario);
        } catch (Exception e) {
            throw new Exception("Error inesperado al guardar el usuario (en la facade)", e);
        }
    }

    public UsuarioDTO searchByCuentaAndClave(String cuenta, String clave) throws Exception { 
        try {    
            Usuario usuario = baseService.searchByCuentaAndClave(cuenta, clave);
            return baseMapper.toDTO(usuario);
        } catch (Exception e) {
            throw new Exception("Error inesperado al guardar el usuario (en la facade)", e);
        }
    }

    public UsuarioDTO searchByCuenta(String cuenta) throws Exception {
        try {    
            Usuario usuario = baseService.searchByCuenta(cuenta);
            return baseMapper.toDTO(usuario);
        } catch (Exception e) {
            throw new Exception("Error inesperado al guardar el usuario (en la facade)", e);
        }
    }

    public Optional<UsuarioDTO> buscarPorEmail(String email) throws Exception{
        try {
            return baseService.buscarPorEmail(email)
                            .map(baseMapper::toDTO); // Convierte el Optional<Usuario> a Optional<UsuarioDTO>
        }catch(Exception e) {
            throw new Exception("Error inesperado al buscar el mail (en la facade)", e);
        }
    }
    
    
    
}


    /* 
    UsuarioDTO searchByCuenta(String cuenta) throws Exception;
    UsuarioDTO searchByCuentaAndClave(String cuenta, String clave) throws Exception;
    UsuarioDTO crear(UsuarioCreateDTO usuario) throws Exception;
    UsuarioDTO searchByIdPersona(String idPersona) throws Exception;
    Optional<UsuarioDTO> buscarPorEmail(String email) throws ErrorServiceException;
    */

