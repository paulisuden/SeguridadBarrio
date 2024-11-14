package com.is.servidor_barrio.business.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioCreateDTO;
import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioDTO;
import com.is.servidor_barrio.business.domain.entity.Imagen;
import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.ImagenService;
import com.is.servidor_barrio.business.logic.service.UsuarioService;
import com.is.servidor_barrio.business.mapper.UsuarioMapper;

@Service

public class UsuarioFacadeImpl extends BaseFacadeImpl<Usuario, UsuarioDTO, UsuarioCreateDTO, UsuarioCreateDTO, String> {

    protected final UsuarioService baseService;
    protected final UsuarioMapper baseMapper;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    public UsuarioFacadeImpl(UsuarioService baseService, UsuarioMapper baseMapper) {
        super(baseService, baseMapper);
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    public UsuarioDTO save(UsuarioCreateDTO usuarioCreateDTO) throws Exception {

        try {
            Usuario usuarioEntity = baseMapper.toEntityCreate(usuarioCreateDTO);

            // Convierte la entidad guardada en un DTO para retornar
            Imagen imagen = imagenService.findById(usuarioCreateDTO.getImagenId());
            usuarioEntity.setImagen(imagen);
            // Guarda la entidad Usuario en la base de datos
            Usuario usuarioGuardado = baseService.save(usuarioEntity);
            return baseMapper.toDTO(usuarioGuardado);
        } catch (Exception e) {
            throw new Exception("Error inesperado al guardar el usuario (en la facade)", e);
        }
    }

    @Override
    public UsuarioDTO update(String id, UsuarioCreateDTO usuarioCreateDTO) throws Exception {
        var usuarioEntity = baseService.findById(id);
        baseMapper.toUpdate(usuarioEntity, usuarioCreateDTO);

        Imagen imagen = imagenService.findById(usuarioCreateDTO.getImagenId());
        usuarioEntity.setImagen(imagen);
        var updatedEntity = baseService.update(id, usuarioEntity);
        return baseMapper.toDTO(updatedEntity);
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

    public Optional<UsuarioDTO> buscarPorEmail(String email) throws Exception {
        try {
            return baseService.buscarPorEmail(email)
                    .map(baseMapper::toDTO); // Convierte el Optional<Usuario> a Optional<UsuarioDTO>
        } catch (Exception e) {
            throw new Exception("Error inesperado al buscar el mail (en la facade)", e);
        }
    }
}